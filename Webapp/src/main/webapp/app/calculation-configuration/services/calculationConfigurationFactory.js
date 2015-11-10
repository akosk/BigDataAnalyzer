/**
 * Created by Ákos on 2015.11.23..
 */

(function () {
    "use strict";

    angular.module('app')
        .factory('calculationConfigurationsService', calculationConfigurationsService);

    function calculationConfigurationsService($resource) {
        return $resource('/webapi-calculation-configurations/:id', {id: "@id"}, {
            'update': {method: 'PUT'}
        })
    }

})
();
