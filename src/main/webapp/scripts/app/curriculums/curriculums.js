'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('curriculums', {
                parent: 'site',
                url: '/curriculums',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/curriculums/curriculums.html',
                        controller: 'CurriculumsController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('curriculums');
                        return $translate.refresh();
                    }]
                }
            });
    });




