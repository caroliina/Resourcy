'use strict';

angular.module('resourcyApp').controller('AdditionalStudyDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'AdditionalStudy', 'CurriculumVitae',
        function($scope, $stateParams, $uibModalInstance, entity, AdditionalStudy, CurriculumVitae) {

        $scope.additionalStudy = entity;
        $scope.curriculumvitaes = CurriculumVitae.query();
        $scope.load = function(id) {
            AdditionalStudy.get({id : id}, function(result) {
                $scope.additionalStudy = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('resourcyApp:additionalStudyUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.additionalStudy.id != null) {
                AdditionalStudy.update($scope.additionalStudy, onSaveSuccess, onSaveError);
            } else {
                AdditionalStudy.save($scope.additionalStudy, onSaveSuccess, onSaveError);
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
