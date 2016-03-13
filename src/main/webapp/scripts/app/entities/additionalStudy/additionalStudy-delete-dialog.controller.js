'use strict';

angular.module('resourcyApp')
	.controller('AdditionalStudyDeleteController', function($scope, $uibModalInstance, entity, AdditionalStudy) {

        $scope.additionalStudy = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            AdditionalStudy.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
