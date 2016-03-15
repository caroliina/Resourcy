'use strict';

angular.module('resourcyApp')
    .controller('WorkAssignmentDetailController', function ($scope, $rootScope, $stateParams, entity, WorkAssignment, WorkExperience, GovernmentWorkExperience) {
        $scope.workAssignment = entity;
        $scope.load = function (id) {
            WorkAssignment.get({id: id}, function(result) {
                $scope.workAssignment = result;
            });
        };
        var unsubscribe = $rootScope.$on('resourcyApp:workAssignmentUpdate', function(event, result) {
            $scope.workAssignment = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
