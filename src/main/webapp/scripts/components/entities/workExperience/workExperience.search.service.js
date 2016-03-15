'use strict';

angular.module('resourcyApp')
    .factory('WorkExperienceSearch', function ($resource) {
        return $resource('api/_search/workExperiences/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
