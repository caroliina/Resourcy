'use strict';

angular.module('resourcyApp')
    .factory('AdditionalSkill', function ($resource, DateUtils) {
        return $resource('api/additionalSkills/:id', {}, {
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
