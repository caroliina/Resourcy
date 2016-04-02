'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('insert', {
                parent: 'cv',
                url: '/insert',
                data: {
                    authorities: ['ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/cv/insert/insert.html',
                        controller: 'InsertController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('cv');
                        return $translate.refresh();
                    }]
                }
            }).state('insert.new', {
                parent: 'insert',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                }
            });
    });
