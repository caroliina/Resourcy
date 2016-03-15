'use strict';

angular.module('resourcyApp').controller('GovernmentWorkExperienceDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'GovernmentWorkExperience', 'CurriculumVitae', 'GovernmentProject', 'WorkAssignment',
        function($scope, $stateParams, $uibModalInstance, $q, entity, GovernmentWorkExperience, CurriculumVitae, GovernmentProject, WorkAssignment) {

        $scope.governmentWorkExperience = entity;
        $scope.curriculumvitaes = CurriculumVitae.query();
        $scope.governmentprojects = GovernmentProject.query({filter: 'governmentworkexperience-is-null'});
        $q.all([$scope.governmentWorkExperience.$promise, $scope.governmentprojects.$promise]).then(function() {
            if (!$scope.governmentWorkExperience.governmentProjectId) {
                return $q.reject();
            }
            return GovernmentProject.get({id : $scope.governmentWorkExperience.governmentProjectId}).$promise;
        }).then(function(governmentProject) {
            $scope.governmentprojects.push(governmentProject);
        });
        $scope.workassignments = WorkAssignment.query();
        $scope.load = function(id) {
            GovernmentWorkExperience.get({id : id}, function(result) {
                $scope.governmentWorkExperience = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('resourcyApp:governmentWorkExperienceUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.governmentWorkExperience.id != null) {
                GovernmentWorkExperience.update($scope.governmentWorkExperience, onSaveSuccess, onSaveError);
            } else {
                GovernmentWorkExperience.save($scope.governmentWorkExperience, onSaveSuccess, onSaveError);
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
