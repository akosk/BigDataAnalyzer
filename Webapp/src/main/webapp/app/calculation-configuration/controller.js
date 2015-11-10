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
        $scope.calculationConfiguration = {};
        $scope.calculationConfigurations = [];

        $scope.calculations = [
            {
                id: 'count',
                name: 'Számolás',
                groups: [
                    {
                        id: 'count',
                        name: 'Számolandó mező',
                        min: 1,
                        max: 1
                    }
                ]
            },
            {
                id: 'min',
                name: 'Minimum',
                groups: [
                    {
                        id: 'min',
                        name: 'Számolandó mező',
                        min: 1,
                        max: 1
                    }
                ]
            },
            {
                id: 'max',
                name: 'Maximum',
                groups: [
                    {
                        id: 'max',
                        name: 'Számolandó mező',
                        min: 1,
                        max: 1
                    }
                ]
            },
            {
                id: 'avg',
                name: 'Átlag',
                groups: [
                    {
                        id: 'avg',
                        name: 'Számolandó mező',
                        min: 1,
                        max: 1
                    }
                ]
            },
            {
                id: 'disp',
                name: 'Szórás',
                groups: [
                    {
                        id: 'disp',
                        name: 'Számolandó mező',
                        min: 1,
                        max: 1
                    }
                ]
            },

            {
                id: 'regression',
                name: 'Lineáris regresszió számítás',
                groups: [
                    {
                        id: 'x',
                        name: 'X',
                        min: 1
                    },
                    {
                        id: 'y',
                        name: 'Y',
                        min: 1,
                        max: 1
                    }

                ]
            },
            {
                id: 'regression',
                name: 'Logisztikus regresszió számítás',
                groups: [
                    {
                        id: 'x',
                        name: 'X',
                        min: 1
                    },
                    {
                        id: 'y',
                        name: 'Y',
                        min: 1,
                        max: 1
                    }

                ]
            },
            {
                id: 'regression',
                name: 'Főkomponens analízis',
                groups: [
                    {
                        id: 'x',
                        name: 'X',
                        min: 1
                    },
                    {
                        id: 'y',
                        name: 'Y',
                        min: 1,
                        max: 1
                    }

                ]
            },
            {
                id: 'regression',
                name: 'K-közép klaszterezés',
                groups: [
                    {
                        id: 'x',
                        name: 'X',
                        min: 1
                    },
                    {
                        id: 'y',
                        name: 'Y',
                        min: 1,
                        max: 1
                    }

                ]
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
            $scope.selectedCalculation = calculation;
            $scope.calculationConfiguration = getDefaultCalculationConfiguration($scope.selectedDataSources, calculation);
        };

        $scope.getCalculationTemplate = function () {
            var path = 'app/calculation-configuration/templates/calculations/';
            var template = $scope.selectedCalculation != null ? $scope.selectedCalculation.id : 'empty';
            return path + template + '.html';
        };

        $scope.saveConfiguration = function () {
            $scope.calculationConfiguration.create_time = moment().format("YYYY-MM-DD HH:mm:ss");
            //$scope.calculationConfiguration.job_done = 2;
            $scope.calculationConfigurations.push($scope.calculationConfiguration);
            $('.modal-conf').modal('hide');
        };
    }


    function getDefaultCalculationConfiguration(selectedDataSources, calculation) {
        var config = {};
        config.numberOfColumns = getNumberOfColumns(selectedDataSources);

        config.columns = [];
        for (var i = 0; i < config.numberOfColumns; i++) config.columns.push({});

        return config;
    }

    function getNumberOfColumns(selectedDataSources) {
        var max = 0;
        selectedDataSources.forEach(
            function (item) {
                var columnsCount = item.table.columns.length;
                if (max < columnsCount) max = columnsCount;
            }
        );

        return max;
    }


})();