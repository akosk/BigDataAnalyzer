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
                columnConditions: '=condition'
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
                        if ($scope.columnConditions===undefined || !($scope.columnConditions instanceof Array)) {
                            $scope.columnConditions=[];
                        }

                        if ($scope.newConditionItems.length == 0) return;
                        if (!($scope.newConditionItems[0].property &&
                            $scope.newConditionItems[0].operator &&
                            $scope.newConditionItems[0].value)) return;

                        if ($scope.newConditionItems.length > 1) {
                            var newItem = {columnConditions: []};
                            $scope.newConditionItems.forEach(function (item) {
                                newItem.columnConditions.push({
                                    property: item.property,
                                    operator: item.operator,
                                    value: item.value
                                });
                            })
                        } else {
                            var item = $scope.newConditionItems[0];
                            var newItem = {
                                property: item.property,
                                operator: item.operator,
                                value: item.value
                            };
                        }

                        $scope.newConditionItems=[{}];
                        $scope.columnConditions.push(newItem);


                    }

                    $scope.addBlankConditionItem = function () {
                        $scope.newConditionItems.push({});
                    }

                    $scope.removeLastConditionItem= function () {
                        $scope.newConditionItems.pop();
                    }

                    $scope.selectedPropertyChanged = function (item) {

                    }

                    $scope.removeLvl1 = function (item) {
                        $scope.columnConditions = $scope.columnConditions
                            .filter(function (el) {
                                return el !== item;
                            });
                    }
                    $scope.removeLvl2 = function (parent, item) {
                        parent.columnConditions = parent.columnConditions
                            .filter(function (el) {
                                return el !== item;
                            });
                        if (parent.columnConditions.length == 0) {
                            $scope.columnConditions = $scope.columnConditions
                                .filter(function (el) {
                                    return el !== parent;
                                });
                        } else if(parent.columnConditions.length==1) {
                            parent.property=item.property;
                            parent.operator=item.operator;
                            parent.value=item.value;
                            delete parent.columnConditions;
                        }
                    }
                }
            ]

        }
    }


})();

