/**
 * Created by Ákos on 2015.09.25..
 */

(function () {
    "use strict";

    angular.module('app')
        .directive('dsCard', dsCard);

    function dsCard() {
        return {
            templateUrl: "app/calculation-configuration/directives/dsCard.html",
            restrict: "E"
        }
    }


})();

