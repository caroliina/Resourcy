'use strict';

angular.module('resourcyApp').controller('GovernmentProjectDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'GovernmentProject', 'GovernmentWorkExperience', 'Technology',
        function($scope, $stateParams, $uibModalInstance, entity, GovernmentProject, GovernmentWorkExperience, Technology) {

        $scope.governmentProject = entity;
        $scope.governmentworkexperiences = GovernmentWorkExperience.query();
        $scope.technologys = Technology.query();
        $scope.load = function(id) {
            GovernmentProject.get({id : id}, function(result) {
                $scope.governmentProject = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('resourcyApp:governmentProjectUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.governmentProject.id != null) {
                GovernmentProject.update($scope.governmentProject, onSaveSuccess, onSaveError);
            } else {
                GovernmentProject.save($scope.governmentProject, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
