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
                condition: '=condition'
                //isolatedExpressionFoo:'&'
            },
            controller: ['$scope', '$element', '$attrs',
                function ($scope, $element, $attrs) {

                    $scope.newCondition = {};
                    $scope.click = function () {
                        console.log('click!');
                    }

                    $scope.selectedPropertyChanged=function (item) {

                    }

                    $scope.removeLvl1 = function (item) {
                        $scope.condition.conditions = $scope.condition.conditions
                            .filter(function (el) {
                                return el !== item;
                            });
                    }
                    $scope.removeLvl2 = function (parent, item) {
                        parent.conditions = parent.conditions
                            .filter(function (el) {
                                return el !== item;
                            });
                        if (parent.conditions.length == 0) {
                            $scope.condition.conditions = $scope.condition.conditions
                                .filter(function (el) {
                                    return el !== parent;
                                });
                        }
                    }
                }
            ]

        }
    }


})();

