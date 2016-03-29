'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('employeeCVList', {
                parent: 'admin',
                url: '/cv-list',
                data: {
                    authorities: ['ROLE_ADMIN'],
                    pageTitle: 'cv-list.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/employees/employees.html',
                        controller: 'EmployeeCVListController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('employeeCVList');
                        return $translate.refresh();
                    }]
                }
            });
    });
