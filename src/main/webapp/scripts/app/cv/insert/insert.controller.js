'use strict';

angular.module('resourcyApp')
    .controller('InsertController', function ($scope, $state,$http,Employee,ParseLinks,EmployeeSearch,Education) {
        
        $scope.cvs = [];
        $scope.selected = {value: 0};    
        $scope.cvID = 0;
        $scope.educations = [];
        $scope.newEducation = {
           institution : null,
           periodStart : null,
           periodEnd : null,
           speciality : null,
           degree : null,
        }
        $scope.workExperience = [];
        $scope.newExperience = {
           position : null,
           periodStart : null,
           periodEnd : null,
           location : null,
           organization : null
        }
        $scope.additionalLanguage = [];
        $scope.newadditionallanguage = {
           language : null,
           speaking : null,
           writing : null,
        }
        $scope.additionalStudy = [];
        $scope.newadditionalStudy = {
           institution : null,
           periodStart : null,
           periodEnd : null,
           description : null
        }
        $scope.additionalSkill = [];
        $scope.newadditionalSkill = {
           type : null,
           description : null
        }
        $scope.govWorkExperience = [];
        $scope.newgovExperience = {
            gov: {
                position : null,
                periodStart : null,
                periodEnd : null,
                personalWorkHours:null,
            },
            project: {
                buyer:null,
                curriculumVitaeId:null,
                serviceName:null,
                totalProjectWorkHours:null

            }
           
        
        }

        $scope.govTechnology = [];
        $scope.newgovTechnology = {
            type : null,
            description : null,
           
        }

        console.log($scope.employees);
        $scope.addEducation = function() {

                $scope.educations.push($scope.newEducation);
                $scope.newEducation = {
                   institution : null,
                   periodStart : null,
                   periodEnd : null,
                   speciality : null,
                   degree : null,
                }
        
        };
        $scope.addXP = function() {
                $scope.workExperience.push($scope.newExperience);
                $scope.newExperience = {
                   position : null,
                   periodStart : null,
                   periodEnd : null,
                   location : null,
                   organization : null,
                }
        };
        $scope.addlanguage = function() {
                $scope.additionalLanguage.push($scope.newadditionallanguage);
                $scope.newadditionallanguage = {
                   language : null,
                   speaking : null,
                   writing : null,
                }
        };
        $scope.addstudy = function() {
                $scope.additionalStudy.push($scope.newadditionalStudy);
                $scope.newadditionalStudy = {
                   institution : null,
                   periodStart : null,
                   periodEnd : null,
                   description : null,
                }
        };
        $scope.addskill = function() {
                $scope.additionalSkill.push($scope.newadditionalSkill);
                $scope.newadditionalSkill = {
                   institution : null,
                   periodStart : null,
                   periodEnd : null,
                   description : null,
                }
        };
        $scope.addgovpro = function(index) {
                console.log(index)
                $scope.newgovTechnology['govid']=index;
                $scope.govTechnology.push($scope.newgovTechnology);
                $scope.newgovTechnology = {
                   type : null,
                   description : null,

                }
                console.log($scope.govTechnology);
        };
        $scope.addgov = function() {
                $scope.govWorkExperience.push($scope.newgovExperience);
                $scope.newgovExperience = {
                    gov: {
                        position : null,
                        periodStart : null,
                        periodEnd : null,
                        personalWorkHours:null,
                        curriculumVitaeId:null
                    },
                    project: {
                        buyer:null,
                        curriculumVitaeId:null,
                        serviceName:null,
                        totalProjectWorkHours:null

                    }
                   
                
                }
        };

        $scope.saveEducation = function(id) {
            $.each($scope.educations, function(i,val){
                val['curriculumVitaeId'] = id;
                $http.put("api/educations",val).then(function(response){
                    console.log(response)
                });
            })
        };
        $scope.saveGovXP = function(id) {
            $.each($scope.govWorkExperience, function(i,val){
                $http.put("api/governmentProjects",val.project).then(function(response){
                    val.gov['curriculumVitaeId'] = id;
                    val.gov['governmentProjectId'] = response.data.id;
                    $http.put("api/governmentWorkExperiences",val.gov).then(function(data){
                        console.log(data)
                    });
                    console.log(response)
                });

            })
        };
        $scope.saveLxanguage = function(id) {
            $.each($scope.additionalLanguage, function(i,val){
                val['curriculumVitaeId'] = id;
                $http.put("api/languageSkills",val).then(function(response){
                    console.log(response)
                });
            })
        };
        $scope.saveStudy = function(id) {
            $.each($scope.additionalStudy, function(i,val){
                val['curriculumVitaeId'] = id;
                $http.put("api/additionalStudys",val).then(function(response){
                    console.log(response)
                });
            })
        };
        $scope.saveXP = function(id) {
            $.each($scope.workExperience, function(i,val){
                val['curriculumVitaeId'] = id;
                $http.put("api/workExperiences",val).then(function(response){
                    console.log(response)
                });
            })
        };
        $scope.saveSkills = function(id) {
            $.each($scope.additionalSkill, function(i,val){
                val['curriculumVitaeId'] = id;
                $http.put("api/additionalSkills",val).then(function(response){
                    console.log(response)
                });
            })
        };
        $scope.save_cv = function(){
            if($scope.cvForm.$valid){
                $http.put("api/employees",$scope.persons).then(function(response){
                    console.log(response)
                    $scope.employeeId={};
                    $scope.employeeId['employeeId'] = response.data.id;
                    console.log($scope.employeeId)
                    $http.put("api/curriculumVitaes",$scope.employeeId).then(function(datas){
                        console.log(datas);
                        $scope.cvID = datas.data.id;
                        $scope.saveEducation($scope.cvID);
                        $scope.saveXP($scope.cvID);
                        $scope.saveStudy($scope.cvID);
                        $scope.saveLanguage($scope.cvID);
                        $scope.saveGovXP($scope.cvID);
                        $scope.saveSkills($scope.cvID);

                    })
                });

                console.log($scope.persons);
            }else{
                console.log('error');
            }
        }

    });
