 'use strict';

angular.module('resourcyApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-resourcyApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-resourcyApp-params')});
                }
                return response;
            }
        };
    });
