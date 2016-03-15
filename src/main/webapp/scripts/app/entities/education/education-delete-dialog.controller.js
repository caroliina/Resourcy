'use strict';

angular.module('resourcyApp')
	.controller('EducationDeleteController', function($scope, $uibModalInstance, entity, Education) {

        $scope.education = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Education.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
