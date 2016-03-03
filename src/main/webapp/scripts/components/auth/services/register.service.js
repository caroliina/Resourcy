'use strict';

angular.module('resourcyApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


