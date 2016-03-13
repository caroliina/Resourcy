'use strict';

angular.module('resourcyApp')
    .controller('AdditionalStudyDetailController', function ($scope, $rootScope, $stateParams, entity, AdditionalStudy, CurriculumVitae) {
        $scope.additionalStudy = entity;
        $scope.load = function (id) {
            AdditionalStudy.get({id: id}, function(result) {
                $scope.additionalStudy = result;
            });
        };
        var unsubscribe = $rootScope.$on('resourcyApp:additionalStudyUpdate', function(event, result) {
            $scope.additionalStudy = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
