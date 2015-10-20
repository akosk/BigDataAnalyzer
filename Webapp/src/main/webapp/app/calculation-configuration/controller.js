/**
 * Created by Ákos on 2015.06.22..
 */

(function () {
    "use strict";

    angular.module('app')
        .controller('CalculationConfigurationController', CalculationConfigurationController);


    function CalculationConfigurationController($scope, $route, dataService, dragulaService) {

        $scope.isBusy = false;
        $scope.dataService = dataService;
        $scope.availableDataSources = [];
        $scope.selectedDataSources = [];
        $scope.selectedCalculation = null;
        $scope.calculations = [
            {
                id: 'COUNT',
                name: 'Számolás'
            },
            {
                id: 'REGRESSION',
                name: 'Regresszió számítás'
            }
        ];

        $scope.queryDataSources = function () {
            $scope.isBusy = true;
            dataService.queryDataSources()
                .then(
                function () {
                    $scope.availableDataSources = [];
                    $scope.selectedDataSources = [];

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

        dragulaService.options($scope, 'first-bag', {
            revertOnSpill: true
        });

        $scope.queryDataSources();
        $('#spinner').hide();


        $scope.$on('first-bag.drop', function (e, el, container, source) {
            if (container.attr('id') == source.attr('id')) return;

            var filter = function (o) {
                return o != el.scope().item;
            };

            switch (container.attr('id')) {
                case 'available-datasources':
                    $scope.selectedDataSources = $scope.selectedDataSources.filter(filter);
                    $scope.availableDataSources.push(el.scope().item);

                    break;
                case 'selected-datasources':
                    $scope.availableDataSources = $scope.availableDataSources.filter(filter);
                    $scope.selectedDataSources.push(el.scope().item);
                    break;
            }

        });

        $scope.selectCalculation = function (calculation) {
            console.dir(calculation);
            $scope.selectedCalculation = calculation;
        }

    }


})();