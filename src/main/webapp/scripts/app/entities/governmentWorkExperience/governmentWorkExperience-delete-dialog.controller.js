'use strict';

angular.module('resourcyApp')
	.controller('GovernmentWorkExperienceDeleteController', function($scope, $uibModalInstance, entity, GovernmentWorkExperience) {

        $scope.governmentWorkExperience = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            GovernmentWorkExperience.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
