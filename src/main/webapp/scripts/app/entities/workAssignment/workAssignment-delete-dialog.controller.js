'use strict';

angular.module('resourcyApp')
	.controller('WorkAssignmentDeleteController', function($scope, $uibModalInstance, entity, WorkAssignment) {

        $scope.workAssignment = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            WorkAssignment.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
