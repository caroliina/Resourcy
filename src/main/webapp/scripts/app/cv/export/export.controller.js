'use strict';

angular.module('resourcyApp')
    .controller('ExportController', function ($scope, $state, $http, Restangular, $window) {

        $scope.editmode = false;
        $scope.sorting = true;
        $scope.personData = false;
        $scope.persons = {};
        $scope.eduopened = [];
        $scope.birthdayOpen = [];

        //calendars
        $scope.birthdayopen = function ($event) {
            $scope.birthdayOpen[0] = true;
        };
        $scope.eduopen = function ($event, key) {
            $scope.eduopened[key] = true;
        };
        $scope.educlosed = [];
        $scope.educlose = function ($event, key) {
            $scope.educlosed[key] = true;
        };
        $scope.workopened = [];
        $scope.workopen = function ($event, key) {
            $scope.workopened[key] = true;
        };
        $scope.workclosed = [];
        $scope.workclose = function ($event, key) {
            $scope.workclosed[key] = true;
        };
        $scope.govopened = [];
        $scope.govopen = function ($event, key) {
            $scope.govopened[key] = true;
        };
        $scope.govclosed = [];
        $scope.govclose = function ($event, key) {
            $scope.govclosed[key] = true;
        };
        $scope.studyopened = [];
        $scope.studyopen = function ($event, key) {
            $scope.studyopened[key] = true;
        };
        $scope.studyclosed = [];
        $scope.studyclose = function ($event, key) {
            $scope.studyclosed[key] = true;
        };
        //endcalendars

        $scope.exportView = function () {

            var boxes = $(":checkbox:checked");
            var doc = new jsPDF('p', 'pt');
            var endPos;
            var res = doc.autoTableHtmlToJson(document.getElementById("personal"));
            
            doc.text("Curriculum Vitae", 40, 50);
            doc.autoTable(res.columns, res.data, {startY:120});
            $.map(boxes, function(box) {
                var tableName = box.id.substr(0, box.id.indexOf('_'));
                endPos = doc.autoTableEndPosY();
                var res = doc.autoTableHtmlToJson(document.getElementById(tableName));
                doc.autoTable(res.columns, res.data, {
                    startY: endPos ? endPos + 10 : false });
            });

            doc.output("dataurlnewwindow");
        };

        $scope.loadEmployee = function () {

            Restangular.one("api").one("currentEmployee").get().then(function (resp) {
                $scope.persons = resp;
                $scope.personID = resp.id;
                $scope.loadCurriculum();
                $scope.personData = true;
            }, function (error) {
                $scope.editmode = true;
                $scope.sorting = false;
                console.log(error)
            });
        }
        $scope.loadEmployee();
        $scope.loadCurriculum = function () {
            Restangular.one("api").one("curriculums").get().then(function (resp) {
                $scope.cv_id = resp.id;
                $scope.educations = resp.educations;
                $scope.additionalStudy = resp.additionalStudys;
                $scope.additionalLanguage = resp.languageSkills;
                $scope.additionalSkill = resp.additionalSkills;
                $scope.workExperience = resp.workExperiences;
                $scope.govWorkExperience = resp.governmentWorkExperiences;
            });
            Restangular.one('api').one('workExperiences/positions').get().then(function (response) {
                $scope.positions = response;
            })
            Restangular.one('api').one('governmentProjects').get().then(function (response) {
                $scope.projects = response;
            })

        }
        $scope.getProject = "";


        //edits
        $scope.editEdu = function (id) {
            if ($scope.educations[id].editmode === true) {
                $scope.saveEdu(id);
                $scope.educations[id].editmode = false
            } else {
                $scope.educations[id].editmode = true
            }
        }

        $scope.editLang = function (id) {
            if ($scope.additionalLanguage[id].editmode === true) {
                $scope.saveLang(id);
                $scope.additionalLanguage[id].editmode = false
            } else {
                $scope.additionalLanguage[id].editmode = true
            }
        }

        //endedtis

        $scope.newEducation = {
            institution: null,
            periodStart: null,
            periodEnd: null,
            speciality: null,
            degree: null,
        }
        $scope.newadditionalStudy = {
            institution: null,
            periodStart: null,
            periodEnd: null,
            description: null,
        }
        $scope.workXP = {
            position: null,
            periodStart: null,
            periodEnd: null,
            location: null,
            organization: null,
            workAssignments: []
        }
        $scope.newPorject = {
            governmentProject: {
                serviceName: null,
                buyer: null,
                totalProjectWorkHours: null,
                technologies: []
            }
        }
        $scope.newGovXP = {
            position: null,
            periodStart: null,
            periodEnd: null,
            personalWorkHours: null,
            governmentProjectId: null,
            workAssignments: [],
            governmentProject: {
                id: null,
                serviceName: null,
                buyer: null,
                totalProjectWorkHours: null,
                technologies: []
            }
        }
        $scope.newTech = {
            type: null,
            description: null
        }
        $scope.newxpTask = {
            description: null
        }
        $scope.newSkill = {
            type: null,
            description: null,
            experience: null
        }
        $scope.newLanguage = {
            language: null,
            speaking: null,
            writing: null,
        }

        //adding new line
        $scope.addEducation = function () {
            $scope.newEducation.editmode = true;
            $scope.educations.push($scope.newEducation);
            $scope.newEducation = {
                institution: null,
                periodStart: null,
                periodEnd: null,
                speciality: null,
                degree: null,
            }
        }
        $scope.addSkills = function () {
            $scope.newSkill.editmode = true;
            $scope.additionalSkill.push($scope.newSkill);
            $scope.newSkill = {
                type: null,
                description: null,
                experience: null
            }
        }

        $scope.addLanguage = function () {
            $scope.newLanguage.editmode = true;
            $scope.additionalLanguage.push($scope.newLanguage);
            $scope.newLanguage = {
                language: null,
                speaking: null,
                writing: null,
            }
        }
        $scope.addStudy = function () {
            $scope.newadditionalStudy.editmode = true;
            $scope.additionalStudy.push($scope.newadditionalStudy);
            $scope.newadditionalStudy = {
                institution: null,
                periodStart: null,
                periodEnd: null,
                description: null,
            }
        }
        $scope.addworkXP = function () {
            $scope.workXP.editmode = true;
            $scope.workExperience.push($scope.workXP);
            $scope.workXP = {
                position: null,
                periodStart: null,
                periodEnd: null,
                location: null,
                organization: null,
                workAssignments: []
            }
        }

        $scope.addGov = function () {
            angular.element('#togglemodal').trigger('click');
        }
        $scope.addProject = function () {
            if ($scope.position.$valid) {
                if ($scope.getProject != "new") {

                    Restangular.one('api').one('governmentProjects/' + $scope.getProject).get().then(function (response) {
                        $scope.addGovXP(response);
                        angular.element('#togglemodal').trigger('click');

                    })
                } else {
                    $http.put("api/governmentProjects", $scope.newPorject).then(function (response) {
                        $scope.addGovXP(response.data);
                        angular.element('#togglemodal').trigger('click');
                    })
                }
                $scope.getProject = "";
            }

        }
        $scope.addGovXP = function (response) {
            $scope.newGovXP.governmentProject.buyer = response.buyer;
            $scope.newGovXP.governmentProject.id = response.id;
            $scope.newGovXP.governmentProjectId = response.id;
            $scope.newGovXP.governmentProject.totalProjectWorkHours = response.totalProjectWorkHours;
            $scope.newGovXP.governmentProject.serviceName = response.serviceName;
            $scope.newGovXP.governmentProject.technologies = response.technologies;
            $scope.newGovXP.editmode = true;
            $scope.govWorkExperience.push($scope.newGovXP);
            $scope.newGovXP = {
                position: null,
                periodStart: null,
                periodEnd: null,
                personalWorkHours: null,
                governmentProjectId: null,
                workAssignments: [],
                governmentProject: {
                    id: null,
                    serviceName: null,
                    buyer: null,
                    totalProjectWorkHours: null,
                    technologies: []
                }
            }
        }

        $scope.addGovTechs = function (key) {
            $scope.govWorkExperience[key].governmentProject.technologies.push($scope.newTech);
            $scope.newTech = {
                type: null,
                description: null,
            }
        }
        $scope.addxpTasks = function (key) {
            $scope.workExperience[key].workAssignments.push($scope.newxpTask);
            $scope.newxpTask = {
                description: null,
            }
        }
        $scope.addGovAssignment = function (key) {
            $scope.govWorkExperience[key].workAssignments.push($scope.newTech);
            $scope.newTech = {
                type: null,
                description: null,
            }
        }
        // end adding line
        // remove line
        $scope.removeEdu = function (id) {
            if ($scope.educations[id].id) {
                $http.delete("api/educations/" + $scope.educations[id].id).then(function (response) {

                })
            }
            $scope.educations.splice(id, 1);
        }
        $scope.removeLang = function (id) {
            if ($scope.additionalLanguage[id].id) {
                $http.delete("api/languageSkills/" + $scope.additionalLanguage[id].id).then(function (response) {

                })
            }
            $scope.additionalLanguage.splice(id, 1);
        }
        $scope.removeStudy = function (id) {
            if ($scope.additionalStudy[id].id) {
                $http.delete("api/additionalStudys/" + $scope.additionalStudy[id].id).then(function (response) {

                })
            }
            $scope.additionalStudy.splice(id, 1);
        }
        $scope.removeSkill = function (id) {
            if ($scope.additionalSkill[id].id) {
                $http.delete("api/additionalSkills/" + $scope.additionalSkill[id].id).then(function (response) {

                })
            }
            $scope.additionalSkill.splice(id, 1);
        }
        $scope.removeGovAssignment = function (govxpid, assignid) {
            if ($scope.govWorkExperience[govxpid].workAssignments[assignid].id) {
                $http.delete("api/workAssignments/" + $scope.govWorkExperience[govxpid].workAssignments[assignid].id).then(function (response) {

                })
            }
            $scope.govWorkExperience[govxpid].workAssignments.splice(assignid, 1);
        }
        $scope.removeGovTechs = function (govxpid, techid) {
            if ($scope.govWorkExperience[govxpid].governmentProject.technologies[techid].id) {
                $http.delete("api/technologys/" + $scope.govWorkExperience[govxpid].governmentProject.technologies[techid].id).then(function (response) {

                })
            }
            $scope.govWorkExperience[govxpid].governmentProject.technologies.splice(techid, 1);
        }
        $scope.removeXPAssignment = function (workxpid, assignid) {
            if ($scope.workExperience[workxpid].workAssignments[assignid].id) {
                $http.delete("api/workAssignments/" + $scope.workExperience[workxpid].workAssignments[assignid].id).then(function (response) {

                })
            }
            $scope.workExperience[workxpid].workAssignments.splice(assignid, 1);
        }
        $scope.removeXP = function (workxpid) {

            angular.forEach($scope.workExperience[workxpid].workAssignments, function (assign, key) {

                if (assign.id) {
                    $http.delete("api/workAssignments/" + assign.id).then(function (response) {

                    })
                }
            })
            if ($scope.workExperience[workxpid].id) {
                $http.delete("api/workExperiences/" + $scope.workExperience[workxpid].id).then(function (response) {

                })
            }
            $scope.workExperience.splice(workxpid, 1);
        }
        $scope.removeGov = function (govid) {

            angular.forEach($scope.govWorkExperience[govid].workAssignments, function (assign, key) {

                if (assign.id) {
                    $http.delete("api/workAssignments/" + assign.id).then(function (response) {

                    })
                }
            })
            angular.forEach($scope.govWorkExperience[govid].governmentProject.technologies, function (assign, key) {

                if (assign.id) {
                    $http.delete("api/technologys/" + assign.id).then(function (response) {

                    })
                }
            })
            if ($scope.govWorkExperience[govid].id) {
                $http.delete("api/governmentWorkExperiences/" + $scope.govWorkExperience[govid].id).then(function (response) {

                })
            }
            $scope.govWorkExperience.splice(govid, 1);
        }
        //end remove line

        //edits
        $scope.editEdu = function (id) {
            if ($scope.educations[id].editmode === true) {
                $scope.saveEdu(id);
                $scope.educations[id].editmode = false
            } else {
                $scope.educations[id].editmode = true
            }
        }

        $scope.editLang = function (id) {
            if ($scope.additionalLanguage[id].editmode === true) {
                $scope.saveLang(id);
                $scope.additionalLanguage[id].editmode = false
            } else {
                $scope.additionalLanguage[id].editmode = true
            }
        }
        $scope.editSkill = function (id) {
            if ($scope.additionalSkill[id].editmode === true) {
                $scope.saveSkill(id);
                $scope.additionalSkill[id].editmode = false
            } else {
                $scope.additionalSkill[id].editmode = true
            }
        }
        $scope.editStudy = function (id) {
            if ($scope.additionalStudy[id].editmode === true) {
                $scope.saveStudy(id);
                $scope.additionalStudy[id].editmode = false
            } else {
                $scope.additionalStudy[id].editmode = true
            }
        }
        $scope.editGov = function (id) {
            if ($scope.govWorkExperience[id].editmode === true) {
                $scope.saveGov(id);
                $scope.govWorkExperience[id].editmode = false
            } else {
                $scope.govWorkExperience[id].editmode = true
            }
        }
        $scope.editXP = function (id) {
            if ($scope.workExperience[id].editmode === true) {
                $scope.saveXP(id);
                $scope.workExperience[id].editmode = false
            } else {
                $scope.workExperience[id].editmode = true
            }
        }

        //endedtis

        // save lines
        $scope.saveEdu = function (id) {
            $scope.educations[id].curriculumVitaeId = $scope.cv_id;
            $http.put("api/educations", $scope.educations[id]).then(function (response) {
            });
        };

        $scope.saveLang = function (id) {
            $scope.additionalLanguage[id].curriculumVitaeId = $scope.cv_id;
            $http.put("api/languageSkills", $scope.additionalLanguage[id]).then(function (response) {

            });
        };

        $scope.saveSkill = function (id) {
            $scope.additionalSkill[id].curriculumVitaeId = $scope.cv_id;
            $http.put("api/additionalSkills", $scope.additionalSkill[id]).then(function (response) {

            });
        };
        $scope.saveStudy = function (id) {
            $scope.additionalStudy[id].curriculumVitaeId = $scope.cv_id;
            $http.put("api/additionalStudys", $scope.additionalStudy[id]).then(function (response) {
                console.log(response)
            });
        };

        $scope.saveXP = function (id) {
            $scope.workExperience[id].curriculumVitaeId = $scope.cv_id;
            $http.put("api/workExperiences", $scope.workExperience[id]).then(function (response) {

                angular.forEach($scope.workExperience[id].workAssignments, function (assign, key) {

                    assign['workExperienceId'] = response.data.id;
                    $http.put("api/workAssignments", assign).then(function (responses) {

                    })
                })

            });
        };

        $scope.saveGov = function (id) {

            $scope.govWorkExperience[id].curriculumVitaeId = $scope.cv_id;

            $scope.govWorkExperience['governmentProjectId'] = $scope.govWorkExperience[id].governmentProject.id;
            console.log($scope.govWorkExperience[id].periodStart)
            angular.forEach($scope.govWorkExperience[id].governmentProject.technologies, function (assign, key) {
                assign['governmentProjectId'] = $scope.govWorkExperience[id].governmentProject.id;
                $http.put("api/technologys", assign).then(function (responses) {
                })
            })

            $http.post("api/govWorkExperience", $scope.govWorkExperience[id]).then(function (govXP) {
                angular.forEach($scope.govWorkExperience[id].workAssignments, function (assign, key) {
                    assign['governmentWorkExperienceId'] = govXP.data.id;
                    $http.put("api/workAssignments", assign).then(function (responses) {
                    })
                })
            })

        };


        $scope.saveEmployee = function () {
            if ($scope.personData) {
                $http.post("api/employees", $scope.persons).then(function (response) {
                    console.log(response)
                });
            } else {
                Restangular.all("api").all("employee").post($scope.persons).then(function (resp) {
                    console.log(resp)
                    $scope.loadEmployee();
                });
            }

        }
        //endsave lines

    }).directive("contenteditable", function () {
    return {
        require: "ngModel",
        link: function (scope, element, attrs, ngModel) {
            function read() {
                ngModel.$setViewValue(element.html());
            }

            ngModel.$render = function () {
                element.html(ngModel.$viewValue || "");
            };

            element.bind("keydown", function (e) {
                if (e.keyCode === 13) {
                    return false;
                } else {

                }

            });
            function readViewText() {
                var html = element.html();
                // When we clear the content editable the browser leaves a <br> behind
                // If strip-br attribute is provided then we strip this out
                if (attrs.stripBr && html == '<br>') {
                    html = '';
                }
                if (element.find('br')) {
                    html = element.text();
                }
                ngModel.$setViewValue(html);

            }
        }
    };
}).directive('ngConfirmClick', [
    function () {
        return {
            link: function (scope, element, attr) {
                var msg = attr.ngConfirmClick || "Are you sure?";
                var clickAction = attr.confirmedClick;
                element.bind('click', function (event) {
                    if (window.confirm(msg)) {
                        scope.$eval(clickAction)
                    }
                });
            }
        };
    }]).filter("ownerGrouping", function () {
    return _.memoize(function (collection, field) {
        return _.groupBy(collection, function (item) {
            return item.type;
        });
    })
});
