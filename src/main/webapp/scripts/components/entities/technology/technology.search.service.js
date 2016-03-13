'use strict';

angular.module('resourcyApp')
    .factory('TechnologySearch', function ($resource) {
        return $resource('api/_search/technologys/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
