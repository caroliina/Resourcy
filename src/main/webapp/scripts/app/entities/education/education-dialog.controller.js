'use strict';

angular.module('resourcyApp').controller('EducationDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Education', 'CurriculumVitae',
        function($scope, $stateParams, $uibModalInstance, entity, Education, CurriculumVitae) {

        $scope.education = entity;
        $scope.curriculumvitaes = CurriculumVitae.query();
        $scope.load = function(id) {
            Education.get({id : id}, function(result) {
                $scope.education = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('resourcyApp:educationUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.education.id != null) {
                Education.update($scope.education, onSaveSuccess, onSaveError);
            } else {
                Education.save($scope.education, onSaveSuccess, onSaveError);
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
