'use strict';

angular.module('resourcyApp').controller('CurriculumVitaeDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CurriculumVitae', 'Employee', 'Education', 'WorkExperience', 'GovernmentWorkExperience', 'AdditionalStudy', 'LanguageSkill', 'AdditionalSkill','Restangular',
        function($scope, $stateParams, $uibModalInstance, entity, CurriculumVitae, Employee, Education, WorkExperience, GovernmentWorkExperience, AdditionalStudy, LanguageSkill, AdditionalSkill, Restangular) {

        $scope.curriculumVitae = entity;
        $scope.employees = Employee.query();
        $scope.educations = Education.query();
        $scope.workexperiences = WorkExperience.query();
        $scope.governmentworkexperiences = GovernmentWorkExperience.query();
        $scope.additionalstudys = AdditionalStudy.query();
        $scope.languageskills = LanguageSkill.query();
        $scope.additionalskills = AdditionalSkill.query();
        $scope.load = function(id) {
            CurriculumVitae.get({id : id}, function(result) {
                $scope.curriculumVitae = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('resourcyApp:curriculumVitaeUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.curriculumVitae.id != null) {
                CurriculumVitae.update($scope.curriculumVitae, onSaveSuccess, onSaveError);
            } else {
                CurriculumVitae.save($scope.curriculumVitae, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.getLanguageTypes = function () {
            Restangular.all("api").all("curriculumVitaes").all("languageTypes").getList().then(function (resp) {
                $scope.languageTypes = resp;
            });
        }
}]);
