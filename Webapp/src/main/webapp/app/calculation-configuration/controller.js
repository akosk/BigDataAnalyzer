/**
 * Created by Ákos on 2015.06.22..
 */

(function () {
    "use strict";

    angular.module('app')
        .controller('CalculationConfigurationController', CalculationConfigurationController);


    function CalculationConfigurationController($scope, $route, dataService) {

        $scope.isBusy = false;
        $scope.dataService = dataService;
        $scope.availableDataSources = [];

        $scope.queryDataSources = function () {
            $scope.isBusy = true;
            dataService.queryDataSources()
                .then(
                function () {
                    $scope.availableDataSources = [];

                    dataService.dataSources.forEach(
                        function (dataSource) {
                            dataSource.databases.forEach(
                                function (database) {
                                    database.tables.forEach(
                                        function (table) {
                                            $scope.availableDataSources.push({
                                                dataSource: dataSource,
                                                database: database,
                                                table: table
                                            });
                                        }
                                    );
                                }
                            );
                        }
                    );


                },
                function () {
                    $scope.initError = true;
                    swal("Kommunikációs hiba!", "Az adatforrások betöltése sikertelen.", "error");
                }
            )
                .then(function () {
                    $scope.isBusy = false;
                });
        };

        $scope.queryDataSources();
        $('#spinner').hide();

    }


})();