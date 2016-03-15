'use strict';

angular.module('resourcyApp')
    .controller('GovernmentProjectController', function ($scope, $state, GovernmentProject, GovernmentProjectSearch) {

        $scope.governmentProjects = [];
        $scope.loadAll = function() {
            GovernmentProject.query(function(result) {
               $scope.governmentProjects = result;
            });
        };
        $scope.loadAll();


        $scope.search = function () {
            GovernmentProjectSearch.query({query: $scope.searchQuery}, function(result) {
                $scope.governmentProjects = result;
            }, function(response) {
                if(response.status === 404) {
                    $scope.loadAll();
                }
            });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.governmentProject = {
                buyer: null,
                serviceName: null,
                totalProjectWorkHours: null,
                id: null
            };
        };
    });
