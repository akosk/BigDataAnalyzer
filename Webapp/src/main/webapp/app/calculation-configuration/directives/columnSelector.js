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
            restrict: "E",
            controller: ['$scope', '$element', '$attrs',
                function ($scope, $element, $attrs) {

                    if (!($scope.calculationConfiguration.columnConfigs instanceof Array)) {
                        $scope.calculationConfiguration.columnConfigs = [];
                    }

                    $scope.newColumnConfig = {};

                    $scope.addColumnConfig = function () {
                        if (!($scope.newColumnConfig.columnName &&
                            $scope.newColumnConfig.type)) return;


                        $scope.calculationConfiguration.columnConfigs.push($scope.newColumnConfig);
                        $scope.newColumnConfig = {};
                    };

                    $scope.removeItem=function(item) {
                        $scope.calculationConfiguration.columnConfigs = $scope.calculationConfiguration.columnConfigs
                            .filter(function (el) {
                                return el !== item;
                            });

                    }
                }
            ]
        }
    }


})();

