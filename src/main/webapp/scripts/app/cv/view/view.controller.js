'use strict';

angular.module('resourcyApp')
    .controller('ViewController', function ($scope, $state,$http,Restangular,$window) {
        console.log($state.current.name)
        $scope.editmode = false;
        $scope.sorting = true;
        $scope.eduopened = [];
        $scope.eduopen = function($event,key) {
          $scope.eduopened[key] = true;
        };
        $scope.educlosed = [];
        $scope.educlose = function($event,key) {
          $scope.educlosed[key] = true;
        };
        $scope.workopened = [];
        $scope.workopen = function($event,key) {
          $scope.workopened[key] = true;
        };
        $scope.workclosed = [];
        $scope.workclose = function($event,key) {
          $scope.workclosed[key] = true;
        };
        $scope.govopened = [];
        $scope.govopen = function($event,key) {
          $scope.govopened[key] = true;
        };
        $scope.govclosed = [];
        $scope.govclose = function($event,key) {
          $scope.govclosed[key] = true;
        };
        $scope.studyopened = [];
        $scope.studyopen = function($event,key) {
          $scope.studyopened[key] = true;
        };
        $scope.studyclosed = [];
        $scope.studyclose = function($event,key) {
          $scope.studyclosed[key] = true;
        };
        $scope.changeView=function(){
          console.log('tere')
          if(!$scope.editmode){
            $scope.loadCurriculum();
            $scope.editmode = true;
            $scope.sorting = false;
          }else{
            $scope.editmode = false;
            $scope.sorting = true;
          }
        };

        
        Restangular.one("api").one("currentEmployee").get().then(function (resp) {
           
            $scope.persons = resp;
            $scope.personID = resp.id;
            
        });
        $scope.loadCurriculum=function(){

          Restangular.one("api").one("curriculums").get().then(function (resp) {
              
              $scope.cv_id = resp.id;
              $scope.educations = resp.educations;
              $scope.additionalStudy = resp.additionalStudys;
              $scope.additionalLanguage = resp.languageSkills;
              $scope.additionalSkill = resp.additionalSkills;
              $scope.workExperience = resp.workExperiences;
              $scope.govWorkExperience = resp.governmentWorkExperiences;
              console.log(resp)
          });

        }
        $scope.loadCurriculum();
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
            workAssignments:{
              description:null
            }
        }
        $scope.newGovXP = {
            position: null,
            periodStart: null,
            periodEnd: null,
            personalWorkHours: null,
            workAssignments: [],
            governmentProject:{
              serviceName:null,
              buyer:null,
              totalProjectWorkHours:null,
              technologies:[]
            }
        }
        $scope.newTech = {
          type:null,
          description:null
        }
        $scope.newSkill = {
            type: null,
            description: null,
            experience:null
        }
        $scope.newLanguage = {
            language: null,
            speaking: null,
            writing: null,
        }
        $scope.addEducation = function(){
            $scope.educations.push($scope.newEducation);
            console.log($scope.educations)
            $scope.newEducation = {
                institution: null,
                periodStart: null,
                periodEnd: null,
                speciality: null,
                degree: null,
            }
        }
        $scope.addSkills = function(){
            $scope.additionalSkill.push($scope.newSkill);
            $scope.newSkill = {
                type: null,
                description: null,
                experience:null
            }
        }
        
        $scope.addLanguage = function(){
            $scope.additionalLanguage.push($scope.newLanguage);
            $scope.newLanguage = {
                language: null,
                speaking: null,
                writing: null,
            }
        }
        $scope.addStudy = function(){
            $scope.additionalStudy.push($scope.newadditionalStudy);
            $scope.newadditionalStudy = {
                institution: null,
                periodStart: null,
                periodEnd: null,
                description: null,
            }
        }
        $scope.addworkXP = function(){
            $scope.workExperience.push($scope.workXP);
            $scope.workXP = {
                position: null,
                periodStart: null,
                periodEnd: null,
                location: null,
                organization: null,
                workAssignments:{
                  description:null
                }
            }
        }
        $scope.addGov = function(){
            $scope.govWorkExperience.push($scope.newGovXP);
            $scope.newGovXP = {
                position: null,
                periodStart: null,
                periodEnd: null,
                personalWorkHours: null,
                workAssignments: [],
                governmentProject:{
                  serviceName:null,
                  buyer:null,
                  totalProjectWorkHours:null,
                  technologies:[]
                }
            }
        }
        $scope.addGovTechs = function(key){
            console.log($scope.govWorkExperience[key]);

            $scope.govWorkExperience[key].governmentProject.technologies.push($scope.newTech);
            $scope.newTech = {
                type: null,
                description: null,
            }
        }
        $scope.addGovAssignment = function(key){
            console.log($scope.govWorkExperience[key]);

            $scope.govWorkExperience[key].workAssignments.push($scope.newTech);
            $scope.newTech = {
                type: null,
                description: null,
            }
        }
        $scope.removeEdu = function (id) {
            if ($scope.educations[id].id) {
                Education.delete({id: $scope.educations[id].id});
            } else {
            }
            $scope.educations.splice(id, 1);
        }
        $scope.saveCV = function(){
          angular.forEach($scope.educations,function(val,key){
            val['curriculumVitaeId'] = $scope.cv_id;
            $http.put("api/educations", val).then(function (response) {
              console.log(response)
            });
          })
          angular.forEach($scope.workExperience,function(val,key){
            val['curriculumVitaeId'] = $scope.cv_id;
            //console.log(val)
            $http.put("api/workExperiences", val).then(function (response) {
              console.log(response)
              angular.forEach(val.workAssignments,function(assign,key){

                val['workExperienceId'] = response.id;
                $http.put("api/workAssignments", val).then(function (responses) {
                  console.log(responses)
                })
              })
                
            });
          })
          angular.forEach($scope.additionalStudy,function(val,key){
            val['curriculumVitaeId'] = $scope.cv_id;
            //console.log(val)
            $http.put("api/additionalStudys", val).then(function (response) {
              console.log(response)                
            });
          })
          angular.forEach($scope.additionalLanguage,function(val,key){
            val['curriculumVitaeId'] = $scope.cv_id;
            //console.log(val)
            $http.put("api/languageSkills", val).then(function (response) {
              console.log(response)                
            });
          })
          angular.forEach($scope.additionalSkill,function(val,key){
            val['curriculumVitaeId'] = $scope.cv_id;
            //console.log(val)
            $http.put("api/additionalSkills", val).then(function (response) {
              console.log(response)                
            });
          })
          angular.forEach($scope.govWorkExperience,function(val,key){
            val['curriculumVitaeId'] = $scope.cv_id;
            //console.log(val)
            
      
          })
          $scope.editmode = false;
          //$window.location.reload();

        }

    }).directive("contenteditable", function() {
        return {
          require: "ngModel",
          link: function(scope, element, attrs, ngModel) {
            function read() {
              ngModel.$setViewValue(element.html());
            }

            ngModel.$render = function() {
              element.html(ngModel.$viewValue || "");
            };

            element.bind("keydown", function(e) {
               if (e.keyCode === 13) {
                  return false;
                }else{

                }
              
            });
             function readViewText() {
                  var html = element.html();
                  // When we clear the content editable the browser leaves a <br> behind
                  // If strip-br attribute is provided then we strip this out
                  if (attrs.stripBr && html == '<br>') {
                      html = '';
                  }
                  if(element.find('br')){
                    html = element.text();
                  }
                  ngModel.$setViewValue(html);

              }
          }
        };
      }).filter("ownerGrouping", function() {
        return _.memoize(function(collection, field) {
          return _.groupBy(collection, function(item) {
            return item.type;
          });
        })
});
