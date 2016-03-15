'use strict';

angular.module('resourcyApp')
    .factory('WorkExperience', function ($resource, DateUtils) {
        return $resource('api/workExperiences/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.periodStart = DateUtils.convertLocaleDateFromServer(data.periodStart);
                    data.periodEnd = DateUtils.convertLocaleDateFromServer(data.periodEnd);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.periodStart = DateUtils.convertLocaleDateToServer(data.periodStart);
                    data.periodEnd = DateUtils.convertLocaleDateToServer(data.periodEnd);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.periodStart = DateUtils.convertLocaleDateToServer(data.periodStart);
                    data.periodEnd = DateUtils.convertLocaleDateToServer(data.periodEnd);
                    return angular.toJson(data);
                }
            }
        });
    });
