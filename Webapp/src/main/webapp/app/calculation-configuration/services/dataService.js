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
                url: 'data/datasources-mock.json',
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


        return {
            queryDataSources: _queryDataSources,
            dataSources: _dataSources
        };
    }

})
();