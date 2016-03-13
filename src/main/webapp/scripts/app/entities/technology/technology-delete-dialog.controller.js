'use strict';

angular.module('resourcyApp')
	.controller('TechnologyDeleteController', function($scope, $uibModalInstance, entity, Technology) {

        $scope.technology = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Technology.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
