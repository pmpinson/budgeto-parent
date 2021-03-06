'use strict';

define(['angular'], function(angular) {

    /**
    * define provider for manage infinite loader globaly
    * available config : setMessage to define message show with infinite loader
    * config : method to get the config : return {getMessage()}
    * show / hide : method to show and hide the infinite loader. Add a div with loader add the end of the body
    */
    function InfiniteLoaderProvider() {
        var message = 'Wait';

        var $infiniteLoaderProvider = {

            setMessage: function (value) {
                message = value;
            },

            $get: ['$document', '$log', function ($document, $log) {
                $log.debug('budgeto.infiniteLoader : load $infiniteLoader');

                var cpt = 0;
                var $infiniteLoader = {};
                var body = $document.find('body').eq(0);
                var element = angular.element('<div class="infinite-loader infinite-loader-default hidden"><p>' + message + '</p></div>');
                body.append(element);

                $infiniteLoader.config = function () {
                    return {
                        getMessage: function () {
                            return message;
                        }
                    };
                };

                $infiniteLoader.show = function () {
                    cpt = cpt + 1;
                    element.removeClass('hidden');
                };

                $infiniteLoader.hide = function () {
                    cpt = cpt - 1 ;
                    if (cpt < 0) {
                        cpt = 0;
                    }
                    if (cpt === 0) {
                        element.addClass('hidden');
                    }
                };

                return $infiniteLoader;
            }]
        };

        return $infiniteLoaderProvider;
    }

    var moduleDefinition = {
        name: 'budgeto.infiniteLoader',
        dependencies: [
        ],
        module: undefined
    };

    // module definition
    moduleDefinition.module = angular.module(moduleDefinition.name, moduleDefinition.dependencies);

    moduleDefinition.module.provider('$infiniteLoader', InfiniteLoaderProvider);

    return moduleDefinition;
});