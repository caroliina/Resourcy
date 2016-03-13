'use strict';

angular.module('resourcyApp')
    .factory('CurriculumVitaeSearch', function ($resource) {
        return $resource('api/_search/curriculumVitaes/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
