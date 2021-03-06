/**
 * Created by Ákos on 2014.12.11..
 */


(function () {
    "use strict";

    angular.module('app')
        .factory('dataService', dataService);

    function dataService($http, $q) {
        var _dataSources = [];


        var _queryDataSources = function () {
            var deferred = $q.defer();
            $http({
                url: 'webapi/datasources',
                method: "POST",
                params: {}
            })
                .then(function (result) {
                    angular.copy(result.data.datasources, _dataSources);
                    deferred.resolve();
                }, function () {
                    deferred.reject();
                });
            return deferred.promise;
        };

        var _saveConfiguration = function (config) {
            var deferred = $q.defer();
            $http({
                url: 'webapi/datasources',
                method: "GET",
                params: {}
            })
                .then(function (result) {
                    angular.copy(result.data.datasources, _dataSources);
                    deferred.resolve();
                }, function () {
                    deferred.reject();
                });
            return deferred.promise;
        };

        var _getColumns = function (dataSourceName, dataBaseName, tableName) {
            var columns=[];
            _dataSources.forEach(
                function (dataSource) {
                    if (dataSource.name == dataSourceName) {
                        dataSource.databases.forEach(
                            function (database) {
                                if (database.sql_name == dataBaseName) {
                                    database.tables.forEach(
                                        function (table) {
                                            if (table.sql_name==tableName) {
                                                columns=table.columns;
                                            }
                                        }
                                    );
                                }
                            }
                        );
                    }
                }
            );
            return columns;

        };


        return {
            queryDataSources: _queryDataSources,
            dataSources: _dataSources,
            saveConfiguration: _saveConfiguration,
            getColumns: _getColumns
        };
    }

})
();