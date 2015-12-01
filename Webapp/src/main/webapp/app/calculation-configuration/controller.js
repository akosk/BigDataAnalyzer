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
        $scope.dataSources=[];
        $scope.availableDataSources = [];

        $scope.selectedCalculation = null;   // Az új konfig során kiválasztott calc
        $scope.calculationConfiguration = {}; // Az új konfig
        $scope.calculationConfigurations = calculationConfigurationsService.query(); // Az összes konfig a táblázatos listában
        $scope.calculations = getCalculations(); // Kalkuláció típusok

        $scope.new = newModell;
        $scope.delete = deleteModell;
        $scope.edit = editModell;

        $scope.queryDataSources = function () {
            $scope.isBusy = true;
            dataService.queryDataSources()
                .then(
                function () {

                    $scope.dataSources=[];

                    dataService.dataSources.forEach(
                        function (dataSource) {
                            dataSource.databases.forEach(
                                function (database) {
                                    database.tables.forEach(
                                        function (table) {
                                            $scope.dataSources.push({
                                                dataSourceName: dataSource.name,
                                                databaseName: database.sql_name,
                                                tableName: table.sql_name
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
                        $scope.calculationConfiguration.selectedTables =
                            $scope.calculationConfiguration.selectedTables.filter(filter);
                        $scope.availableDataSources.push(el.scope().item);

                        break;
                    case 'selected-datasources':
                        $scope.availableDataSources = $scope.availableDataSources.filter(filter);
                        $scope.calculationConfiguration.selectedTables.push(el.scope().item);
                        break;
                }

                $scope.conditionConfiguration = getConditionConfig();
            });

        });

        $scope.selectCalculation = function (calculation) {
            $scope.selectedCalculation = calculation;
            $scope.calculationConfiguration.calculation_id = calculation.id;

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
                $scope.calculationConfigurations = calculationConfigurationsService.query();
                $('.modal-conf').modal('hide');
            });


        };

        function editModell(modell) {
            $scope.calculationConfiguration=modell;
            $scope.queryDataSources();

            $scope.selectedCalculation=_.find($scope.calculations, function(i){
                return i.id== modell.calculation_id;
            });

            $scope.dataSources.forEach(function (item){
                var ds=_.find($scope.calculationConfiguration.selectedTables, function(i){
                    return i.tableName== item.tableName;
                });

                if (!ds) {
                    $scope.availableDataSources.push(item);
                }


                //$scope.calculationConfiguration.selectedTables = [];

            });
            $('.modal-conf').modal('show');
        }

        function newModell() {
            $scope.calculationConfiguration={};
            $scope.calculationConfiguration.selectedTables = [];

            $scope.dataSources.forEach(function (item){
                $scope.availableDataSources.push(item);
            });
            $('.modal-conf').modal('show');
        }

        function deleteModell(modell) {

            swal({
                title: "Biztosan törli?",
                text: "Törlés után már nincs lehetőség az adat visszaállítására!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Igen, törlöm!",
                closeOnConfirm: true
            }, function () {
                calculationConfigurationsService.delete({id: modell.id}, function () {
                    $scope.calculationConfigurations = calculationConfigurationsService.query();
                });
            });
        }

        function getConditionConfig() {
            var config = {
                properties: []
            };

            $scope.calculationConfiguration.selectedTables.forEach(function (item) {
                var columns = dataService.getColumns(item.dataSourceName, item.databaseName, item.tableName);
                columns.forEach(function (column) {
                    config.properties.push(
                        {
                            id: item.tableName+'.'+column.sql_name,
                            name: item.tableName+'.'+column.sql_name
                        }
                    );

                });
            });
            return config;
        }

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