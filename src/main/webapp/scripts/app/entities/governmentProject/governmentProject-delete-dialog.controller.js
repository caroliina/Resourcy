'use strict';

angular.module('resourcyApp')
	.controller('GovernmentProjectDeleteController', function($scope, $uibModalInstance, entity, GovernmentProject) {

        $scope.governmentProject = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            GovernmentProject.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
