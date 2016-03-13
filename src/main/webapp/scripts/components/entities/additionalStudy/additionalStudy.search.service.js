'use strict';

angular.module('resourcyApp')
    .factory('AdditionalStudySearch', function ($resource) {
        return $resource('api/_search/additionalStudys/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
