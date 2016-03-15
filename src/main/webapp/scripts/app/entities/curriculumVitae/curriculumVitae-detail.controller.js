'use strict';

angular.module('resourcyApp')
    .controller('CurriculumVitaeDetailController', function ($scope, $rootScope, $stateParams, entity, CurriculumVitae, Employee, Education, WorkExperience, GovernmentWorkExperience, AdditionalStudy, LanguageSkill, AdditionalSkill) {
        $scope.curriculumVitae = entity;
        $scope.load = function (id) {
            CurriculumVitae.get({id: id}, function(result) {
                $scope.curriculumVitae = result;
            });
        };
        var unsubscribe = $rootScope.$on('resourcyApp:curriculumVitaeUpdate', function(event, result) {
            $scope.curriculumVitae = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
