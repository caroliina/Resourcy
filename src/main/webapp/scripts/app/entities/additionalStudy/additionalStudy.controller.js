'use strict';

angular.module('resourcyApp')
    .controller('AdditionalStudyController', function ($scope, $state, AdditionalStudy, AdditionalStudySearch) {

        $scope.additionalStudys = [];
        $scope.loadAll = function() {
            AdditionalStudy.query(function(result) {
               $scope.additionalStudys = result;
            });
        };
        $scope.loadAll();


        $scope.search = function () {
            AdditionalStudySearch.query({query: $scope.searchQuery}, function(result) {
                $scope.additionalStudys = result;
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
            $scope.additionalStudy = {
                periodStart: null,
                periodEnd: null,
                institution: null,
                description: null,
                id: null
            };
        };
    });
