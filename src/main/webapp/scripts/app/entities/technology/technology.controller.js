'use strict';

angular.module('resourcyApp')
    .controller('TechnologyController', function ($scope, $state, Technology, TechnologySearch) {

        $scope.technologys = [];
        $scope.loadAll = function() {
            Technology.query(function(result) {
               $scope.technologys = result;
            });
        };
        $scope.loadAll();


        $scope.search = function () {
            TechnologySearch.query({query: $scope.searchQuery}, function(result) {
                $scope.technologys = result;
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
            $scope.technology = {
                type: null,
                description: null,
                id: null
            };
        };
    });
