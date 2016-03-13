'use strict';

angular.module('resourcyApp')
    .controller('WorkAssignmentController', function ($scope, $state, WorkAssignment, WorkAssignmentSearch) {

        $scope.workAssignments = [];
        $scope.loadAll = function() {
            WorkAssignment.query(function(result) {
               $scope.workAssignments = result;
            });
        };
        $scope.loadAll();


        $scope.search = function () {
            WorkAssignmentSearch.query({query: $scope.searchQuery}, function(result) {
                $scope.workAssignments = result;
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
            $scope.workAssignment = {
                description: null,
                id: null
            };
        };
    });
