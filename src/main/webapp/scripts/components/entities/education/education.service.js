'use strict';

angular.module('resourcyApp')
    .factory('Education', function ($resource, DateUtils) {
        return $resource('api/educations/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    console.log(data);
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
