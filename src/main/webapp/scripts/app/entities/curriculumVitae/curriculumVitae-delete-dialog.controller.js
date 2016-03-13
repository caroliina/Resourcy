'use strict';

angular.module('resourcyApp')
	.controller('CurriculumVitaeDeleteController', function($scope, $uibModalInstance, entity, CurriculumVitae) {

        $scope.curriculumVitae = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            CurriculumVitae.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
