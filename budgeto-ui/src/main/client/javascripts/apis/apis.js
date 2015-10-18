'use strict';

define(['angular', 'angular-resource'], function(angular) {

    var moduleDefinition = {
        name: 'budgeto.apis',
        dependencies: [
          'ngResource'
        ],
        module: undefined
    };

    // Register angular module
    moduleDefinition.module = angular.module(moduleDefinition.name, moduleDefinition.dependencies);

    /**
     * Resource http to cal apis endpoint
     * @returns {{all: to get all apis from rest endpoint, returning an array of apis in a promise}}
     */
    moduleDefinition.module.factory('ApisResource', ['$resource', '$log', function ($resource, $log) {
        $log.debug('budgeto.apis : load ApiResource');

        return {
            all: function (url) {
                return $resource(url, {}, {}).get({}).$promise;
            }
        };
    }]);

    /**
     * provider to manage apis
     */
    moduleDefinition.module.provider('ApiService', function () {
        var url = 'noUrlSet';

        var $apiServiceProvider = {

            setUrl: function (value) {
                url = value;
            },

            $get: ['$log', 'ApisResource', function ($log, ApisResource) {
                $log.debug('budgeto.apis : load ApiService');

                var apis = [];
                var $apiService = {};

                $apiService.config = function () {
                    return {
                        getUrl: function () {
                            return url;
                        }
                    };
                };

                $apiService.loaded = function () {
                    $log.debug('budgeto.apis : call api to get all available apis');

                    return ApisResource.all(url).then(function(data) {
                        $apiService.loadApis(data);
                        return data;
                    });
                };

                $apiService.loadApis = function (data) {
                    for (var key in data.links) {
                        if (data.links[key].rel !== 'self') {
                            apis.push(data.links[key]);
                        }
                    }
                    $log.debug('budgeto.apis : available apis', apis);
                };

                $apiService.findAll = function () {
                    return apis;
                };

                $apiService.find = function (rel) {
                    return this.getLink(rel, apis);
                };

                $apiService.getLink = function (rel, links) {
                    for (var key in links) {
                        if (links[key].rel === rel) {
                            return links[key];
                        }
                    }
                    return null;
                };

                return $apiService;
            }]
        };

        return $apiServiceProvider;
    });

    return moduleDefinition;
});