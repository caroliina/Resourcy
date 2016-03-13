'use strict';

angular.module('resourcyApp')
    .controller('WorkExperienceDetailController', function ($scope, $rootScope, $stateParams, entity, WorkExperience, CurriculumVitae, WorkAssignment) {
        $scope.workExperience = entity;
        $scope.load = function (id) {
            WorkExperience.get({id: id}, function(result) {
                $scope.workExperience = result;
            });
        };
        var unsubscribe = $rootScope.$on('resourcyApp:workExperienceUpdate', function(event, result) {
            $scope.workExperience = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
