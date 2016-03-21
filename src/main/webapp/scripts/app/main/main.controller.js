'use strict';

angular.module('resourcyApp')
    .controller('MainController', function ($scope, Principal, $http) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        $http({
            method: 'GET',
            url: '/api/curriculumVitaes'
        }).then(function successCallback(response) {
            var curriculumVitaes = response.data;
            var curriculumVitaesSorted = [];
            $.each(curriculumVitaes, function (key, value) {
                setEmployeeName(value);
                if (curriculumVitaesSorted.length > 0) {
                    if (new Date(curriculumVitaesSorted[0].lastModifiedDate) < new Date(value.lastModifiedDate)) {
                        curriculumVitaesSorted.unshift(value);
                    } else {
                        curriculumVitaesSorted.push(value);
                    }
                } else {
                    curriculumVitaesSorted.push(value);
                }
            });
            $scope.curriculumVitaesSorted = curriculumVitaesSorted;
        }, function errorCallback(response) {
        });

        function setEmployeeName(value) {
            $.ajax({
                type: 'GET',
                url: '/api/employees/' + value.employeeId,
                success: function(data){
                    console.log(data);
                    value.employeeName = data.firstName + " " + data.lastName;
                }
            });
        }
    });
