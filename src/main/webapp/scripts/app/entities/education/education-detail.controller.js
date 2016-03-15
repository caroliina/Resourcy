'use strict';

angular.module('resourcyApp')
    .controller('EducationDetailController', function ($scope, $rootScope, $stateParams, entity, Education, CurriculumVitae) {
        $scope.education = entity;
        $scope.load = function (id) {
            Education.get({id: id}, function(result) {
                $scope.education = result;
            });
        };
        var unsubscribe = $rootScope.$on('resourcyApp:educationUpdate', function(event, result) {
            $scope.education = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
