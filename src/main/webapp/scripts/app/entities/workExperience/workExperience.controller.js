'use strict';

angular.module('resourcyApp')
    .controller('WorkExperienceController', function ($scope, $state, WorkExperience, WorkExperienceSearch) {

        $scope.workExperiences = [];
        $scope.loadAll = function() {
            WorkExperience.query(function(result) {
               $scope.workExperiences = result;
            });
        };
        $scope.loadAll();


        $scope.search = function () {
            WorkExperienceSearch.query({query: $scope.searchQuery}, function(result) {
                $scope.workExperiences = result;
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
            $scope.workExperience = {
                position: null,
                periodStart: null,
                periodEnd: null,
                location: null,
                organization: null,
                id: null
            };
        };
    });
