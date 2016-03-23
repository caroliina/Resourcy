'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('update', {
                parent: 'cv',
                url: '/update',
                data: {
                    authorities: ['ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/cv/update/update.html',
                        controller: 'InsertController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('cv');
                        return $translate.refresh();
                    }]
                }
            });
    });
