'use strict';

angular.module('resourcyApp').controller('AdditionalSkillDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'AdditionalSkill', 'CurriculumVitae',
        function($scope, $stateParams, $uibModalInstance, entity, AdditionalSkill, CurriculumVitae) {

        $scope.additionalSkill = entity;
        $scope.curriculumvitaes = CurriculumVitae.query();
        $scope.load = function(id) {
            AdditionalSkill.get({id : id}, function(result) {
                $scope.additionalSkill = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('resourcyApp:additionalSkillUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.additionalSkill.id != null) {
                AdditionalSkill.update($scope.additionalSkill, onSaveSuccess, onSaveError);
            } else {
                AdditionalSkill.save($scope.additionalSkill, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
