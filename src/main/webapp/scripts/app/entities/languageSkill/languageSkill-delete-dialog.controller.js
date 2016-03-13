'use strict';

angular.module('resourcyApp')
	.controller('LanguageSkillDeleteController', function($scope, $uibModalInstance, entity, LanguageSkill) {

        $scope.languageSkill = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            LanguageSkill.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
