'use strict';

angular.module('resourcyApp')
    .factory('GovernmentProject', function ($resource, DateUtils) {
        return $resource('api/governmentProjects/:id', {}, {
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
