/**
 * Created by Ákos on 2015.11.05..
 */


(function () {
    "use strict";

    angular.module('app')
        .factory('kozetModellsService', kozetModellsService);

    function kozetModellsService($resource) {
        return $resource('/webapi/kozet-modells/:id', {id: "@id"}, {
            'update': {method: 'PUT'}
        })
    }

})
();