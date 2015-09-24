/**
 * Created by Ákos on 2015.06.22..
 */

(function () {
    "use strict";

    angular.module('app')
        .config(config);

    function config($routeProvider) {

        $routeProvider.when("/", {
            controller: "CalculationConfigurationController",
            templateUrl: 'app/calculation-configuration/templates/layout.html?ver=' + VERSION,
        });

        $routeProvider.otherwise("/");
    }


})();