'use strict';

angular.module('resourcyApp')
    .controller('CvController', function ($scope, $state,$http,Employee,ParseLinks,EmployeeSearch) {
        $scope.employees = [];
        $scope.cvs = [];
        $http.get("api/curriculumVitaes/")
			    .then(function(response) {
			        $scope.cvs = [response['data']];

			    });
        $scope.getEmployees=function(){
        	Employee.query({page: 0, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                console.log(result);
                for (var i = 0; i < result.length; i++) {
                    $scope.employees.push(result[i]);
                }
            });
		    

        }
        $scope.getEmployees();
       
        
			
         console.log($scope.employees);
    });
