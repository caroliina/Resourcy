'use strict';

angular.module('resourcyApp')
	.controller('AdditionalSkillDeleteController', function($scope, $uibModalInstance, entity, AdditionalSkill) {

        $scope.additionalSkill = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            AdditionalSkill.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
