'use strict';

angular.module('resourcyApp')
    .controller('EmployeeDetailController', function ($scope, $rootScope, $stateParams, entity, Employee, CurriculumVitae) {
        $scope.employee = entity;
        $scope.load = function (id) {
            Employee.get({id: id}, function(result) {
                $scope.employee = result;
            });
        };
        var unsubscribe = $rootScope.$on('resourcyApp:employeeUpdate', function(event, result) {
            $scope.employee = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
