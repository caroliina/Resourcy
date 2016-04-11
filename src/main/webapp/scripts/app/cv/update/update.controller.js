'use strict';

angular.module('resourcyApp')
    .controller('UpdateController', function ($q, $scope, $location, $state, $http, Restangular, Technology, LanguageSkill, AdditionalSkill, AdditionalStudy, GovernmentWorkExperience, GovernmentProject, WorkExperience, Employee, ParseLinks, CurriculumVitae, EmployeeSearch, Education) {
        $scope.datePickerForPerson = {status: {}};
        $scope.datePickerForPerson.status;
        $scope.datePickerForEduPeriodStart = {status: {opened: {}}};
        $scope.datePickerForEduPeriodStart.status.opened = {};
        $scope.datePickerForEduPeriodEnd = {status: {opened: {}}};
        $scope.datePickerForEduPeriodEnd.status.opened = {};
        $scope.datePickerForXPPeriodStart = {status: {opened: {}}};
        $scope.datePickerForXPPeriodStart.status.opened = {};
        $scope.datePickerForXPPeriodEnd = {status: {opened: {}}};
        $scope.datePickerForXPPeriodEnd.status.opened = {};
        $scope.datePickerForGovPeriodStart = {status: {opened: {}}};
        $scope.datePickerForGovPeriodStart.status.opened = {};
        $scope.datePickerForGovPeriodEnd = {status: {opened: {}}};
        $scope.datePickerForGovPeriodEnd.status.opened = {};
        $scope.datePickerForStudyPeriodStart = {status: {opened: {}}};
        $scope.datePickerForStudyPeriodStart.status.opened = {};
        $scope.datePickerForStudyPeriodEnd = {status: {opened: {}}};
        $scope.datePickerForStudyPeriodEnd.status.opened = {};

        Restangular.one("api").one("currentEmployee").get().then(function (resp) {
            $scope.persons = resp;
            $scope.personID = resp.id;
            $scope.datePickerForPerson.status = {opened: false};
        });

        Restangular.one("api").one("curriculums").get().then(function (resp) {
            $scope.cv_id = resp.id;
            $scope.educations = resp.educations;
            $scope.additionalStudy = resp.additionalStudys;
            $scope.additionalLanguage = resp.languageSkills;
            $scope.additionalSkill = resp.additionalSkills;
            $scope.workExperience = resp.workExperiences;
            $scope.govWorkExperience = resp.governmentWorkExperiences;
        });

        $scope.datePickerForPerson = function ($event) {
            $scope.datePickerForPerson.status = {opened: true};
        };
        $scope.datePickerForEduPeriodStartOpen = function ($event, $key) {
            $scope.datePickerForEduPeriodStart.status.opened[$key] = true;
        };
        $scope.datePickerForEduPeriodEndOpen = function ($event, $key) {
            $scope.datePickerForEduPeriodEnd.status.opened[$key] = true;
        };
        $scope.datePickerForXPPeriodStartOpen = function ($event, $key) {
            $scope.datePickerForXPPeriodStart.status.opened[$key] = true;
        };
        $scope.datePickerForXPPeriodEndOpen = function ($event, $key) {
            $scope.datePickerForXPPeriodEnd.status.opened[$key] = true;
        };
        $scope.datePickerForGovPeriodStartOpen = function ($event, $key) {
            $scope.datePickerForGovPeriodStart.status.opened[$key] = true;
        };
        $scope.datePickerForGovPeriodEndOpen = function ($event, $key) {
            $scope.datePickerForGovPeriodEnd.status.opened[$key] = true;
        };
        $scope.datePickerForStudyPeriodStartOpen = function ($event, $key) {
            $scope.datePickerForStudyPeriodStart.status.opened[$key] = true;
        };
        $scope.datePickerForStudyPeriodEndOpen = function ($event, $key) {
            $scope.datePickerForStudyPeriodEnd.status.opened[$key] = true;
        };

        $scope.cvs = [];
        $scope.selected = {value: 0};
        $scope.cvID = $location.search().id;
        $scope.educations = [];
        $scope.newEducation = {
            institution: null,
            periodStart: null,
            periodEnd: null,
            speciality: null,
            degree: null,
        }
        $scope.workExperience = [];
        $scope.newExperience = {
            position: null,
            periodStart: null,
            periodEnd: null,
            location: null,
            organization: null
        }
        $scope.additionalLanguage = [];
        $scope.newadditionallanguage = {
            language: null,
            speaking: null,
            writing: null,
        }
        $scope.additionalStudy = [];
        $scope.newadditionalStudy = {
            institution: null,
            periodStart: null,
            periodEnd: null,
            description: null
        }
        $scope.additionalSkill = [];
        $scope.newadditionalSkill = {
            type: null,
            description: null
        }
        $scope.govWorkExperience = [];
        $scope.newgovExperience = {
            gov: {
                position: null,
                periodStart: null,
                periodEnd: null,
                personalWorkHours: null,
            },
            project: {
                buyer: null,
                curriculumVitaeId: null,
                serviceName: null,
                totalProjectWorkHours: null

            },
            tech: []
        }
        $scope.govTechnology = [];
        $scope.newgovTechnology = {
            governmentProjectId: null,
            id: null,
            type: null,
            description: null,
        }

        $scope.addEducation = function () {
            $scope.educations.push($scope.newEducation);
            $scope.newEducation = {
                institution: null,
                periodStart: null,
                periodEnd: null,
                speciality: null,
                degree: null,
            }
        };
        $scope.addXP = function () {
            $scope.workExperience.push($scope.newExperience);
            $scope.newExperience = {
                position: null,
                periodStart: null,
                periodEnd: null,
                location: null,
                organization: null,
            }

        };
        $scope.addlanguage = function () {
            $scope.additionalLanguage.push($scope.newadditionallanguage);
            $scope.newadditionallanguage = {
                language: null,
                speaking: null,
                writing: null,
            }
        };
        $scope.addstudy = function () {
            $scope.additionalStudy.push($scope.newadditionalStudy);
            $scope.newadditionalStudy = {
                institution: null,
                periodStart: null,
                periodEnd: null,
                description: null,
            }
        };
        $scope.addskill = function () {
            $scope.additionalSkill.push($scope.newadditionalSkill);
            $scope.newadditionalSkill = {
                institution: null,
                periodStart: null,
                periodEnd: null,
                description: null,
            }
        };
        $scope.addgovpro = function (index) {
            $scope.govWorkExperience[index].governmentProject.technologies.push($scope.newgovTechnology);
            $scope.newgovTechnology = {
                governmentProjectId: null,
                id: null,
                type: null,
                description: null,

            }
        };
        $scope.addgov = function () {
            $scope.govWorkExperience.push($scope.newgovExperience);
            $scope.newgovExperience = {
                gov: {
                    position: null,
                    periodStart: null,
                    periodEnd: null,
                    personalWorkHours: null,
                    curriculumVitaeId: null
                },
                project: {
                    buyer: null,
                    curriculumVitaeId: null,
                    serviceName: null,
                    totalProjectWorkHours: null

                },
                tech: []


            }
        };

        $scope.removeedu = function (id) {
            if ($scope.educations[id].id) {
                Education.delete({id: $scope.educations[id].id});
            } else {
            }
            $scope.educations.splice(id, 1);
        }
        $scope.removexp = function (id) {
            if ($scope.workExperience[id].id) {
                WorkExperience.delete({id: $scope.workExperience[id].id});
            } else {
            }
            $scope.workExperience.splice(id, 1);
        }
        $scope.removestudy = function (id) {

            if ($scope.additionalStudy[id].id) {
                AdditionalStudy.delete({id: $scope.additionalStudy[id].id});
            } else {
            }
            $scope.additionalStudy.splice(id, 1);
        }
        $scope.removelang = function (id) {
            if ($scope.additionalLanguage[id].id) {
                LanguageSkill.delete({id: $scope.additionalLanguage[id].id});
            } else {
            }
            $scope.additionalLanguage.splice(id, 1);
        }
        $scope.removeskill = function (id) {
            if ($scope.additionalSkill[id].id) {
                AdditionalSkill.delete({id: $scope.additionalSkill[id].id});
            } else {
            }
            $scope.additionalSkill.splice(id, 1);
        }
        $scope.removegov = function (id) {
            if ($scope.govWorkExperience[id].id) {
                GovernmentWorkExperience.delete({id: $scope.govWorkExperience[id].id});
            } else {
            }
            $scope.govWorkExperience.splice(id, 1);
        }
        $scope.removetech = function (val, id) {
            if ($scope.govWorkExperience[id].governmentProject.technologies[val].id) {
                Technology.delete({id: $scope.govWorkExperience[id].governmentProject.technologies[val].id});
            } else {
            }
            $scope.govWorkExperience[id].governmentProject.technologies.splice(val, 1);
        }

        $scope.saveEducation = function (id) {
            $.each($scope.educations, function (i, val) {
                val['curriculumVitaeId'] = id;

                $http.put("api/educations", val).then(function (response) {
                });
            })
        };
        $scope.saveGovXP = function (id) {
            $.each($scope.govWorkExperience, function (i, val) {
                $http.put("api/governmentProjects", val.governmentProject).then(function (response) {
                    val['curriculumVitaeId'] = id;
                    val['governmentProjectId'] = response.data.id;
                    $http.put("api/governmentWorkExperiences", val).then(function (data) {
                        console.log(val);
                        $.each(val.governmentProject.technologies, function (i, techs) {
                            techs['governmentProjectId'] = response.data.id;
                            $http.put("api/technologys", techs).then(function (techsave) {
                            })
                        })
                    });
                });
            })
        };
        $scope.saveLanguage = function (id) {
            $.each($scope.additionalLanguage, function (i, val) {
                val['curriculumVitaeId'] = id;
                $http.put("api/languageSkills", val).then(function (response) {
                });
            })
        };
        $scope.saveStudy = function (id) {
            $.each($scope.additionalStudy, function (i, val) {
                val['curriculumVitaeId'] = id;

                $http.put("api/additionalStudys", val).then(function (response) {

                });
            })

        };
        $scope.saveXP = function (id) {
            $.each($scope.workExperience, function (i, val) {
                val['curriculumVitaeId'] = id;

                $http.put("api/workExperiences", val).then(function (response) {

                });
            })
        };
        $scope.saveSkills = function (id) {
            $.each($scope.additionalSkill, function (i, val) {

                val['curriculumVitaeId'] = id;
                $http.put("api/additionalSkills", val).then(function (response) {

                }, function (e) {
                })

            })
        };
        $scope.save_cv = function () {
            if ($scope.cvForm.$valid) {
                $scope.date = new Date($scope.persons.birthday);
                if ($scope.date.getTimezoneOffset() * 60000 > 0) {
                    $scope.date = new Date($scope.date.getTime() + $scope.date.getTimezoneOffset() * 60000);
                } else {
                    $scope.date = new Date($scope.date.getTime() - $scope.date.getTimezoneOffset() * 60000);
                }
                $scope.persons.birthday = $scope.date;
                $http.put("api/employees/", $scope.persons).then(function (response) {
                });
                $q.all([
                    $scope.saveEducation($scope.personID),
                    $scope.saveXP($scope.personID),
                    $scope.saveStudy($scope.personID),
                    $scope.saveLanguage($scope.personID),
                    $scope.saveGovXP($scope.personID),
                    $scope.saveSkills($scope.personID)
                ]).then(function () {


                });
            } else {

            }
        }

    });

