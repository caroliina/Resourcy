'use strict';

angular.module('resourcyApp')
    .factory('GovernmentWorkExperienceSearch', function ($resource) {
        return $resource('api/_search/governmentWorkExperiences/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
