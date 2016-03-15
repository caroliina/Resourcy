'use strict';

angular.module('resourcyApp')
    .factory('LanguageSkillSearch', function ($resource) {
        return $resource('api/_search/languageSkills/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
