'use strict';

angular.module('resourcyApp')
    .controller('TechnologyDetailController', function ($scope, $rootScope, $stateParams, entity, Technology, GovernmentProject) {
        $scope.technology = entity;
        $scope.load = function (id) {
            Technology.get({id: id}, function(result) {
                $scope.technology = result;
            });
        };
        var unsubscribe = $rootScope.$on('resourcyApp:technologyUpdate', function(event, result) {
            $scope.technology = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
