'use strict';

angular.module('resourcyApp')
    .controller('LanguageSkillController', function ($scope, $state, LanguageSkill, LanguageSkillSearch) {

        $scope.languageSkills = [];
        $scope.loadAll = function() {
            LanguageSkill.query(function(result) {
               $scope.languageSkills = result;
            });
        };
        $scope.loadAll();


        $scope.search = function () {
            LanguageSkillSearch.query({query: $scope.searchQuery}, function(result) {
                $scope.languageSkills = result;
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
            $scope.languageSkill = {
                language: null,
                speaking: null,
                writing: null,
                id: null
            };
        };
    });
