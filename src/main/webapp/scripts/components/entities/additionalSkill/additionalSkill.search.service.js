'use strict';

angular.module('resourcyApp')
    .factory('AdditionalSkillSearch', function ($resource) {
        return $resource('api/_search/additionalSkills/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
