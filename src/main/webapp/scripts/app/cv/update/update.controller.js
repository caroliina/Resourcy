'use strict';

angular.module('resourcyApp')
    .controller('UpdateController', function ($q,$scope, $state,$http,LanguageSkill,AdditionalSkill,AdditionalStudy,GovernmentWorkExperience,GovernmentProject,WorkExperience,Employee,ParseLinks,CurriculumVitae,EmployeeSearch,Education) {
        $scope.datePickerForPerson= {status:{}};
        $scope.datePickerForPerson.status;
        $scope.datePickerForEduPeriodStart= {status:{opened: {}}};
        $scope.datePickerForEduPeriodStart.status.opened = {};
        $scope.datePickerForEduPeriodEnd= {status:{opened: {}}};
        $scope.datePickerForEduPeriodEnd.status.opened = {};
        $scope.datePickerForXPPeriodStart= {status:{opened: {}}};
        $scope.datePickerForXPPeriodStart.status.opened = {};
        $scope.datePickerForXPPeriodEnd= {status:{opened: {}}};
        $scope.datePickerForXPPeriodEnd.status.opened = {};
        $scope.datePickerForGovPeriodStart= {status:{opened: {}}};
        $scope.datePickerForGovPeriodStart.status.opened = {};
        $scope.datePickerForGovPeriodEnd= {status:{opened: {}}};
        $scope.datePickerForGovPeriodEnd.status.opened = {};
        $scope.datePickerForStudyPeriodStart= {status:{opened: {}}};
        $scope.datePickerForStudyPeriodStart.status.opened = {};
        $scope.datePickerForStudyPeriodEnd= {status:{opened: {}}};
        $scope.datePickerForStudyPeriodEnd.status.opened = {};
        $scope.personID = $state.params.id;
            Employee.get({id: $scope.personID}, function(result) {
                $scope.persons = result;
            });
        CurriculumVitae.get({id: $scope.personID}, function(result) {
              $scope.cv_id = result;
        });

        $scope.loadPerson = function(){
          Employee.get({id: $scope.personID}, function(result) {
                $scope.persons = result;
                console.log(result)
            });
          $scope.datePickerForPerson.status= {opened : false};

        }
        $scope.loadEdu = function(){
          $scope.educations = [];
          Education.query(function(result) {
              $scope.nr = 0;
              $.each(result,function(i,val){
                if(val.curriculumVitaeId===parseInt($scope.personID)){
                  console.log(val);
                  $scope.educations.push(val);
                  $scope.datePickerForEduPeriodStart.status.opened[ $scope.nr] = false;
                  $scope.datePickerForEduPeriodEnd.status.opened[ $scope.nr] = false;
                  $scope.nr++;
                  
                }
              })
          })
        }
        $scope.loadStudy = function(){
          $scope.additionalStudy = [];
          AdditionalStudy.query(function(result) {
              $scope.nr = 0;
              $.each(result,function(i,val){
                if(val.curriculumVitaeId===parseInt($scope.personID)){

                  $scope.additionalStudy.push(val);
                  $scope.datePickerForStudyPeriodStart.status.opened[ $scope.nr] = false;
                  $scope.datePickerForStudyPeriodEnd.status.opened[ $scope.nr] = false;
                  $scope.nr++;
                  
                }
              })
          })
        }
        $scope.loadLanguage = function(){
          $scope.additionalLanguage = [];
          LanguageSkill.query(function(result) {
              $scope.nr = 0;
              $.each(result,function(i,val){
                if(val.curriculumVitaeId===parseInt($scope.personID)){

                  $scope.additionalLanguage.push(val);
                  
                }
              })
          })
        }
        $scope.loadSkill = function(){
          $scope.additionalSkill = [];
          AdditionalSkill.query(function(result) {
              $scope.nr = 0;
              $.each(result,function(i,val){
                if(val.curriculumVitaeId===parseInt($scope.personID)){

                  $scope.additionalSkill.push(val);
                  $scope.datePickerForStudyPeriodStart.status.opened[ $scope.nr] = false;
                  $scope.datePickerForStudyPeriodEnd.status.opened[ $scope.nr] = false;
                  $scope.nr++;
                  
                }
              })
          })
        }
        $scope.loadXP = function(){
          $scope.workExperience = [];

          WorkExperience.query({curriculumVitaeId: $scope.personID},function(result) {

              $scope.nr = 0;
              $.each(result,function(i,val){
                if(val.curriculumVitaeId===parseInt($scope.personID)){

                  $scope.workExperience.push(val);
                  $scope.datePickerForXPPeriodStart.status.opened[ $scope.nr] = false;
                  $scope.datePickerForXPPeriodEnd.status.opened[ $scope.nr] = false;
                  $scope.nr++;
                  
                }
              })
          })
        }
        $scope.loadGov = function(){
          $scope.govWorkExperience = [];

          GovernmentWorkExperience.query(function(result) {
              
              $scope.nr = 0;
              $.each(result,function(i,val){
                if(val.curriculumVitaeId===parseInt($scope.personID)){
                  GovernmentProject.get({id: val.governmentProjectId},function(result2) {
                    $scope.govWorkExperience.push({gov:val,project:result2});
                    
                  })
                  //$scope.govWorkExperience.push({gov:{},project:{}});

                  $scope.datePickerForGovPeriodStart.status.opened[ $scope.nr] = false;
                  $scope.datePickerForGovPeriodEnd.status.opened[ $scope.nr] = false;
                  $scope.nr++;
                  
                }
              })
          })
        }

        $scope.loadPerson();
        $scope.loadEdu();
        $scope.loadXP();
        $scope.loadGov();
        $scope.loadStudy();
        $scope.loadSkill();
        $scope.loadLanguage();

        $scope.datePickerForPerson = function($event) {
            $scope.datePickerForPerson.status={opened: true};
        };
        $scope.datePickerForEduPeriodStartOpen = function($event,$key) {
            $scope.datePickerForEduPeriodStart.status.opened[$key] = true;
        };
        $scope.datePickerForEduPeriodEndOpen = function($event,$key) {
            $scope.datePickerForEduPeriodEnd.status.opened[$key] = true;
        };
        $scope.datePickerForXPPeriodStartOpen = function($event,$key) {
            $scope.datePickerForXPPeriodStart.status.opened[$key] = true;
        };
        $scope.datePickerForXPPeriodEndOpen = function($event,$key) {
            $scope.datePickerForXPPeriodEnd.status.opened[$key] = true;
        };
        $scope.datePickerForGovPeriodStartOpen = function($event,$key) {
            $scope.datePickerForGovPeriodStart.status.opened[$key] = true;
        };
        $scope.datePickerForGovPeriodEndOpen = function($event,$key) {
            $scope.datePickerForGovPeriodEnd.status.opened[$key] = true;
        };
        $scope.datePickerForStudyPeriodStartOpen = function($event,$key) {
            $scope.datePickerForStudyPeriodStart.status.opened[$key] = true;
        };
        $scope.datePickerForStudyPeriodEndOpen = function($event,$key) {
            $scope.datePickerForStudyPeriodEnd.status.opened[$key] = true;
        };
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
                $scope.newgovTechnology['govid']=index;
                $scope.govTechnology.push($scope.newgovTechnology);
                $scope.newgovTechnology = {
                   type : null,
                   description : null,

                }
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
                });
            })
        };
        $scope.saveGovXP = function(id) {
            $.each($scope.govWorkExperience, function(i,val){
                $http.put("api/governmentProjects",val.project).then(function(response){
                    val.gov['curriculumVitaeId'] = id;
                    val.gov['governmentProjectId'] = response.data.id;
                    $http.put("api/governmentWorkExperiences",val.gov).then(function(data){
                    });
                });

            })
        };
        $scope.saveLanguage = function(id) {
            $.each($scope.additionalLanguage, function(i,val){
                val['curriculumVitaeId'] = id;
                $http.put("api/languageSkills",val).then(function(response){
                });
            })
        };
        $scope.saveStudy = function(id) {
            $.each($scope.additionalStudy, function(i,val){
                val['curriculumVitaeId'] = id;
                $http.put("api/additionalStudys",val).then(function(response){
                });
            })
            
        };
        $scope.saveXP = function(id) {
            $.each($scope.workExperience, function(i,val){
                val['curriculumVitaeId'] = id;
                $http.put("api/workExperiences",val).then(function(response){
                });
            })
        };
        $scope.saveSkills = function(id) {
            $.each($scope.additionalSkill, function(i,val){
                val['curriculumVitaeId'] = id;
                $http.put("api/additionalSkills",val).then(function(response){
                },function(e){})

            })
        };
        $scope.save_cv = function(){
            if($scope.cvForm.$valid){
                console.log($scope.persons.birthday);
                $scope.date = new Date($scope.persons.birthday); 
                if($scope.date.getTimezoneOffset()*60000 > 0){
                  $scope.date = new Date($scope.date.getTime() + $scope.date.getTimezoneOffset()*60000); 
                }else{
                  $scope.date = new Date($scope.date.getTime() - $scope.date.getTimezoneOffset()*60000); 
                };
                
                $scope.persons.birthday = $scope.date; 
                console.log($scope.persons.birthday);
                $http.put("api/employees/",$scope.persons).then(function(response){
                    
                });
                $q.all([
                $scope.saveEducation($scope.personID),
                $scope.saveXP($scope.personID),
                $scope.saveStudy($scope.personID),
                $scope.saveLanguage($scope.personID),
                $scope.saveGovXP($scope.personID),
                $scope.saveSkills($scope.personID),
                ]).then(function(){
                    $scope.loadPerson();
                    $scope.loadEdu();
                    $scope.loadXP();
                    $scope.loadGov();
                    $scope.loadStudy();
                    $scope.loadSkill();
                    $scope.loadLanguage();
                });
                    
                   

            }else{
            }

         

        }

    });
