'use strict';

angular.module('resourcyApp')
    .factory('WorkAssignmentSearch', function ($resource) {
        return $resource('api/_search/workAssignments/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
