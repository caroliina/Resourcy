'use strict';

angular.module('resourcyApp')
    .controller('AdditionalSkillController', function ($scope, $state, AdditionalSkill, AdditionalSkillSearch) {

        $scope.additionalSkills = [];
        $scope.loadAll = function() {
            AdditionalSkill.query(function(result) {
               $scope.additionalSkills = result;
            });
        };
        $scope.loadAll();


        $scope.search = function () {
            AdditionalSkillSearch.query({query: $scope.searchQuery}, function(result) {
                $scope.additionalSkills = result;
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
            $scope.additionalSkill = {
                type: null,
                description: null,
                id: null
            };
        };
    });
