'use strict';

angular.module('resourcyApp')
    .factory('WorkAssignment', function ($resource, DateUtils) {
        return $resource('api/workAssignments/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
