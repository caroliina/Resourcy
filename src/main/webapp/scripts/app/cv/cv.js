'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('cv', {
                parent: 'site',
                url: '/cv',
                data: {
                    authorities: ['ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/cv/cv.html',
                        controller: 'CvController'
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
