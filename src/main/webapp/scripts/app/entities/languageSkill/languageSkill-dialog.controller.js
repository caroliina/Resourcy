'use strict';

angular.module('resourcyApp').controller('LanguageSkillDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'LanguageSkill', 'CurriculumVitae',
        function($scope, $stateParams, $uibModalInstance, entity, LanguageSkill, CurriculumVitae) {

        $scope.languageSkill = entity;
        $scope.curriculumvitaes = CurriculumVitae.query();
        $scope.load = function(id) {
            LanguageSkill.get({id : id}, function(result) {
                $scope.languageSkill = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('resourcyApp:languageSkillUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.languageSkill.id != null) {
                LanguageSkill.update($scope.languageSkill, onSaveSuccess, onSaveError);
            } else {
                LanguageSkill.save($scope.languageSkill, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
