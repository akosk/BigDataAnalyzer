/**
 * Created by Ákos on 2015.11.12..
 */


(function () {
    "use strict";

    angular.module('app')
        .directive('conditionEditor', columnSelector);

    function columnSelector() {
        return {
            templateUrl: "app/calculation-configuration/directives/conditionEditor.html",
            restrict: "E",
            scope: {
                //isolatedAttributeFoo:'@attributeFoo',
                config: '@config',
                condition: '=condition',
                //isolatedExpressionFoo:'&'
            },
            controller: ['$scope', '$element', '$attrs',
                function ($scope, $element, $attrs) {

                    $scope.click = function () {
                        console.log('click!');
                    }
                }
            ]

        }
    }


})();

