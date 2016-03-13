'use strict';

angular.module('resourcyApp')
    .controller('GovernmentWorkExperienceController', function ($scope, $state, GovernmentWorkExperience, GovernmentWorkExperienceSearch) {

        $scope.governmentWorkExperiences = [];
        $scope.loadAll = function() {
            GovernmentWorkExperience.query(function(result) {
               $scope.governmentWorkExperiences = result;
            });
        };
        $scope.loadAll();


        $scope.search = function () {
            GovernmentWorkExperienceSearch.query({query: $scope.searchQuery}, function(result) {
                $scope.governmentWorkExperiences = result;
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
            $scope.governmentWorkExperience = {
                periodStart: null,
                periodEnd: null,
                personalWorkHours: null,
                id: null
            };
        };
    });
