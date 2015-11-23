/**
 * Created by Ákos on 2015.06.22..
 */

(function () {
    "use strict";

    angular.module('app')
        .controller('CalculationConfigurationController', CalculationConfigurationController);


    function CalculationConfigurationController($scope, $route, dataService, dragulaService, calculationConfigurationsService) {

        $scope.isBusy = false;
        $scope.dataService = dataService;
        $scope.availableDataSources = [];

        $scope.selectedCalculation = null;   // Az új konfig során kiválasztott calc
        $scope.calculationConfiguration = {}; // Az új konfig
        $scope.calculationConfigurations = []; // Az összes konfig a táblázatos listában
        $scope.calculations = getCalculations(); // Kalkuláció típusok

        $scope.queryDataSources = function () {
            $scope.isBusy = true;
            dataService.queryDataSources()
                .then(
                function () {
                    $scope.availableDataSources = [];
                    $scope.calculationConfiguration.selectedDataSources = [];

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

            $scope.$apply(function () {
                switch (container.attr('id')) {
                    case 'available-datasources':
                        $scope.calculationConfiguration.selectedDataSources =
                            $scope.calculationConfiguration.selectedDataSources.filter(filter);
                        $scope.availableDataSources.push(el.scope().item);

                        break;
                    case 'selected-datasources':
                        $scope.availableDataSources = $scope.availableDataSources.filter(filter);
                        $scope.calculationConfiguration.selectedDataSources.push(el.scope().item);
                        break;
                }
                //$scope.calculationConfiguration.selectedDataSources.push = function (){
                //    debugger;
                //    return Array.prototype.push.apply(this,arguments);
                //}

                $scope.conditionConfiguration = getConditionConfig();
            });

        });

        $scope.selectCalculation = function (calculation) {
            $scope.selectedCalculation = calculation;
            $scope.calculationConfiguration.selectedCalculation = calculation;
            $.extend(true, $scope.calculationConfiguration, getDefaultCalculationConfiguration($scope.calculationConfiguration.selectedDataSources, calculation));

        };

        $scope.getCalculationTemplate = function () {
            var path = 'app/calculation-configuration/templates/calculations/';
            var template = $scope.selectedCalculation != null ? $scope.selectedCalculation.id : 'empty';
            return path + template + '.html';
        };

        $scope.saveConfiguration = function () {


            $scope.calculationConfiguration.create_time = moment().format("YYYY-MM-DD HH:mm:ss");


            var calculationConfiguration = new calculationConfigurationsService();

            for (var attrname in $scope.calculationConfiguration) {
                calculationConfiguration[attrname] = $scope.calculationConfiguration[attrname];
            }


            calculationConfigurationsService.save(calculationConfiguration, function () {

                $scope.calculationConfigurations.push($scope.calculationConfiguration);
                $('.modal-conf').modal('hide');

                //$location.path('/data-manager');
            });


        };


        function getConditionConfig() {
            var config = {
                properties: []
            }

            $scope.calculationConfiguration.selectedDataSources.forEach(function (ds) {
                ds.table.columns.forEach(function (column) {

                    config.properties.push(
                        {
                            id: column.sql_name,
                            name: column.sql_name
                        }
                    );
                });
            });
            return config;
        }

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

    function getCalculations() {
        return [
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
                id: 'linear-regression',
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
                id: 'logistic-regression',
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
                id: 'principal-analysis',
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
                id: 'kmeans-cluster',
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
    }


})();