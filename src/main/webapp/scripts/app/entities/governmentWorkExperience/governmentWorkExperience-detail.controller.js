'use strict';

angular.module('resourcyApp')
    .controller('GovernmentWorkExperienceDetailController', function ($scope, $rootScope, $stateParams, entity, GovernmentWorkExperience, CurriculumVitae, GovernmentProject, WorkAssignment) {
        $scope.governmentWorkExperience = entity;
        $scope.load = function (id) {
            GovernmentWorkExperience.get({id: id}, function(result) {
                $scope.governmentWorkExperience = result;
            });
        };
        var unsubscribe = $rootScope.$on('resourcyApp:governmentWorkExperienceUpdate', function(event, result) {
            $scope.governmentWorkExperience = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
