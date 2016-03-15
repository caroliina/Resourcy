'use strict';

angular.module('resourcyApp')
    .controller('CurriculumVitaeController', function ($scope, $state, CurriculumVitae, CurriculumVitaeSearch, ParseLinks) {

        $scope.curriculumVitaes = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            CurriculumVitae.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.curriculumVitaes.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.curriculumVitaes = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.search = function () {
            CurriculumVitaeSearch.query({query: $scope.searchQuery}, function(result) {
                $scope.curriculumVitaes = result;
            }, function(response) {
                if(response.status === 404) {
                    $scope.loadAll();
                }
            });
        };

        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.curriculumVitae = {
                id: null
            };
        };
    });
