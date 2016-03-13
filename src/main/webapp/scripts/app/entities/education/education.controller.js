'use strict';

angular.module('resourcyApp')
    .controller('EducationController', function ($scope, $state, Education, EducationSearch) {

        $scope.educations = [];
        $scope.loadAll = function() {
            Education.query(function(result) {
               $scope.educations = result;
            });
        };
        $scope.loadAll();


        $scope.search = function () {
            EducationSearch.query({query: $scope.searchQuery}, function(result) {
                $scope.educations = result;
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
            $scope.education = {
                institution: null,
                periodStart: null,
                periodEnd: null,
                speciality: null,
                degree: null,
                id: null
            };
        };
    });
