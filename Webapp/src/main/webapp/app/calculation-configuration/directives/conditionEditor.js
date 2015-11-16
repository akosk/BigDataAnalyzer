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
                config: '=config',
                condition: '=condition'
                //isolatedExpressionFoo:'&'
            },
            controller: ['$scope', '$element', '$attrs',
                function ($scope, $element, $attrs) {
                    $scope.operators = [
                        {id: "<", name: "<"},
                        {id: "<=", name: "<="},
                        {id: "=", name: "="},
                        {id: ">=", name: ">="},
                        {id: ">", name: ">"},
                        {id: "LIKE", name: "LIKE"},
                        {id: "NOT LIKE", name: "NOT LIKE"},
                    ]

                    $scope.newConditionItems = [{}];

                    $scope.addCondition = function () {
                        var newItem = {conditions: []};
                        $scope.newConditionItems.forEach(function (item) {
                            newItem.conditions.push({
                                property: item.property,
                                operator: item.operator,
                                value: item.value
                            });
                        })
                        $scope.condition.conditions.push(newItem);
                    }

                    $scope.addBlankConditionItem = function () {
                        $scope.newConditionItems.push({});
                    }

                    $scope.selectedPropertyChanged = function (item) {

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

