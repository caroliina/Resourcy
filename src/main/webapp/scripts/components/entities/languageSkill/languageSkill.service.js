'use strict';

angular.module('resourcyApp')
    .factory('LanguageSkill', function ($resource, DateUtils) {
        return $resource('api/languageSkills/:id', {}, {
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
