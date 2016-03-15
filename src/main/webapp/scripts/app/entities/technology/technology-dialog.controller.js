'use strict';

angular.module('resourcyApp').controller('TechnologyDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Technology', 'GovernmentProject',
        function($scope, $stateParams, $uibModalInstance, entity, Technology, GovernmentProject) {

        $scope.technology = entity;
        $scope.governmentprojects = GovernmentProject.query();
        $scope.load = function(id) {
            Technology.get({id : id}, function(result) {
                $scope.technology = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('resourcyApp:technologyUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.technology.id != null) {
                Technology.update($scope.technology, onSaveSuccess, onSaveError);
            } else {
                Technology.save($scope.technology, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
