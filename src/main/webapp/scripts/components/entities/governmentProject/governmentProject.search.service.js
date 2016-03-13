'use strict';

angular.module('resourcyApp')
    .factory('GovernmentProjectSearch', function ($resource) {
        return $resource('api/_search/governmentProjects/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
