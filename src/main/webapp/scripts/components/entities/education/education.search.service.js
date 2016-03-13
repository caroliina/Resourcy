'use strict';

angular.module('resourcyApp')
    .factory('EducationSearch', function ($resource) {
        return $resource('api/_search/educations/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
