'use strict';

angular.module('resourcyApp')
    .controller('LanguageSkillDetailController', function ($scope, $rootScope, $stateParams, entity, LanguageSkill, CurriculumVitae) {
        $scope.languageSkill = entity;
        $scope.load = function (id) {
            LanguageSkill.get({id: id}, function(result) {
                $scope.languageSkill = result;
            });
        };
        var unsubscribe = $rootScope.$on('resourcyApp:languageSkillUpdate', function(event, result) {
            $scope.languageSkill = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
