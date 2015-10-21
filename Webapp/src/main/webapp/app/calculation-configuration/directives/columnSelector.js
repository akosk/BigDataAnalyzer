/**
 * Created by Ákos on 2015.10.21..
 */


(function () {
    "use strict";

    angular.module('app')
        .directive('columnSelector', columnSelector);

    function columnSelector() {
        return {
            templateUrl: "app/calculation-configuration/directives/columnSelector.html",
            restrict: "E"
        }
    }


})();

