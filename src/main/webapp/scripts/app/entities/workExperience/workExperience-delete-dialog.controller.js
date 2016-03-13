'use strict';

angular.module('resourcyApp')
	.controller('WorkExperienceDeleteController', function($scope, $uibModalInstance, entity, WorkExperience) {

        $scope.workExperience = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            WorkExperience.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
