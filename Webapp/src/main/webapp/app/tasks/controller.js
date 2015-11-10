/**
 * Created by Ákos on 2015.06.22..
 */

(function () {
    "use strict";

    angular.module('app')
        .controller('TasksController', TasksController);


    function TasksController($scope, $route, $routeParams, $location, dataService, dragulaService, kozetModellsService) {

        $scope.isBusy = false;
        $scope.tasks = [
            {
                name: "Próba konfiguráció",
                start_time: "2015-11-09 12:00:00",
                completed_time: "2015-11-09 13:30:00",
                status: "Befejezett",
                result: "Sikeresen lefutott"
            },            {
                name: "Próba konfiguráció",
                start_time: "2015-11-09 15:00:00",
                completed_time: "",
                status: "Folyamatban",
                result: ""
            }
        ];


        $('#spinner').hide();
    }

})();