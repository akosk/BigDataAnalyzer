/**
 * Created by Ákos on 2015.06.22..
 */

(function () {
    "use strict";

    angular.module('app')
        .config(config);

    function config($routeProvider) {

        $routeProvider.when("/calculation-configuration", {
            controller: "CalculationConfigurationController",
            templateUrl: 'app/calculation-configuration/templates/layout.html?ver=' + VERSION,
        });

        $routeProvider.when("/tasks", {
            controller: "TasksController",
            templateUrl: 'app/tasks/templates/index.html?ver=' + VERSION,
        });

        $routeProvider.when("/data-manager", {
            controller: "DataManagerController",
            templateUrl: 'app/data-manager/templates/index.html?ver=' + VERSION,
        });

        $routeProvider.when("/data-manager/new", {
            controller: "DataManagerController",
            templateUrl: 'app/data-manager/templates/new.html?ver=' + VERSION,
        });

        $routeProvider.when("/data-manager/:id/edit", {
            controller: "DataManagerController",
            templateUrl: 'app/data-manager/templates/edit.html?ver=' + VERSION,
        });

        $routeProvider.otherwise("/");
    }


})();