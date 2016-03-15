'use strict';

angular.module('resourcyApp')
    .controller('GovernmentProjectDetailController', function ($scope, $rootScope, $stateParams, entity, GovernmentProject, GovernmentWorkExperience, Technology) {
        $scope.governmentProject = entity;
        $scope.load = function (id) {
            GovernmentProject.get({id: id}, function(result) {
                $scope.governmentProject = result;
            });
        };
        var unsubscribe = $rootScope.$on('resourcyApp:governmentProjectUpdate', function(event, result) {
            $scope.governmentProject = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
