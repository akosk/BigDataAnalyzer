/**
 * Created by Ákos on 2015.11.05..
 */


(function () {
    "use strict";

    angular.module('app')
        .factory('modellsService', modellsService);

    function modellsService($resource, configFactoryService) {
        return $resource('/webapi-' + configFactoryService.apiName + '/:id', {id: "@id"}, {
            'update': {method: 'PUT'}
        })
    }

})
();