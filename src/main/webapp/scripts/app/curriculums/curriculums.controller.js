angular.module('resourcyApp')
    .controller('CurriculumsController', function ($scope, $state, Restangular) {

        $scope.refresh = function () {
            Restangular.one('api').one('curriculums').get().then(function(resp){
                $scope.curriculumVitae = resp;
            });
        };
        $scope.refresh();

        $scope.employee = [];
        Restangular.one("api").one("currentEmployee").get().then(function (resp) {
            $scope.employee = resp;
        });


    });