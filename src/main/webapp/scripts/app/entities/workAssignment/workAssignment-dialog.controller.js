'use strict';

angular.module('resourcyApp').controller('WorkAssignmentDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'WorkAssignment', 'WorkExperience', 'GovernmentWorkExperience',
        function($scope, $stateParams, $uibModalInstance, entity, WorkAssignment, WorkExperience, GovernmentWorkExperience) {

        $scope.workAssignment = entity;
        $scope.workexperiences = WorkExperience.query();
        $scope.governmentworkexperiences = GovernmentWorkExperience.query();
        $scope.load = function(id) {
            WorkAssignment.get({id : id}, function(result) {
                $scope.workAssignment = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('resourcyApp:workAssignmentUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.workAssignment.id != null) {
                WorkAssignment.update($scope.workAssignment, onSaveSuccess, onSaveError);
            } else {
                WorkAssignment.save($scope.workAssignment, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
