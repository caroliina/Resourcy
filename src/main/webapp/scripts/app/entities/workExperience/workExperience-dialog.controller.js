'use strict';

angular.module('resourcyApp').controller('WorkExperienceDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'WorkExperience', 'CurriculumVitae', 'WorkAssignment',
        function($scope, $stateParams, $uibModalInstance, entity, WorkExperience, CurriculumVitae, WorkAssignment) {

        $scope.workExperience = entity;
        $scope.curriculumvitaes = CurriculumVitae.query();
        $scope.workassignments = WorkAssignment.query();
        $scope.load = function(id) {
            WorkExperience.get({id : id}, function(result) {
                $scope.workExperience = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('resourcyApp:workExperienceUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.workExperience.id != null) {
                WorkExperience.update($scope.workExperience, onSaveSuccess, onSaveError);
            } else {
                WorkExperience.save($scope.workExperience, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForPeriodStart = {};

        $scope.datePickerForPeriodStart.status = {
            opened: false
        };

        $scope.datePickerForPeriodStartOpen = function($event) {
            $scope.datePickerForPeriodStart.status.opened = true;
        };
        $scope.datePickerForPeriodEnd = {};

        $scope.datePickerForPeriodEnd.status = {
            opened: false
        };

        $scope.datePickerForPeriodEndOpen = function($event) {
            $scope.datePickerForPeriodEnd.status.opened = true;
        };
}]);
