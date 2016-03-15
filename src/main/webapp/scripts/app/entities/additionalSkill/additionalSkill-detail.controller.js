'use strict';

angular.module('resourcyApp')
    .controller('AdditionalSkillDetailController', function ($scope, $rootScope, $stateParams, entity, AdditionalSkill, CurriculumVitae) {
        $scope.additionalSkill = entity;
        $scope.load = function (id) {
            AdditionalSkill.get({id: id}, function(result) {
                $scope.additionalSkill = result;
            });
        };
        var unsubscribe = $rootScope.$on('resourcyApp:additionalSkillUpdate', function(event, result) {
            $scope.additionalSkill = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
