/**
 * Created by Ákos on 2015.06.22..
 */

(function () {
    "use strict";

    angular.module('app')
        .controller('DataManagerController', DataManagerController);


    function DataManagerController($scope, $route, $routeParams, $location, dataService, dragulaService, modellsService, configFactoryService) {

        $scope.isBusy = false;
        $scope.dataService = dataService;

        $scope.formErrors = [];

        if ($routeParams.id) {
            $scope.modell = modellsService.get({id: $routeParams.id});
        } else {
            $scope.modell = {};
        }

        $scope.config=configFactoryService;
        $scope.fieldConfig = configFactoryService.formFieldConfig;
        $scope.gridConfig = configFactoryService.gridConfig;


        $scope.modells = modellsService.query();

        $scope.submit = submit;
        $scope.save = save;
        $scope.update = update;
        $scope.delete = deleteModell;





        function save() {
            $scope.formErrors = [];
            var modell = new modellsService();

            for (var attrname in $scope.modell) {
                modell[attrname] = $scope.modell[attrname];
            }


            if (configFactoryService.formatter) {
                var modellFormatted = configFactoryService.formatter(modell);
            } else {
                var modellFormatted = modell;
            }

            modellsService.save(modellFormatted, function () {
                $location.path('/data-manager');
            });
        }

        function update() {
            $scope.formErrors = [];
            if (configFactoryService.formatter) {
                var modellFormatted = configFactoryService.formatter($scope.modell);
            } else {
                var modellFormatted = $scope.modell;
            }


            modellsService.update({id: $routeParams.id}, modellFormatted, function () {
                $location.path('/data-manager');
            }, function () {
                $scope.formErrors.push({'message': 'Hiba történt a mentés során!'});
            });
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
                modellsService.delete({id: modell.id}, function () {
                    $scope.modells = modellsService.query();
                });
            });
        }

        function submit() {
            if ($routeParams.id) {
                $scope.update();
            } else {
                $scope.save();
            }
        }

        $('#spinner').hide();
    }


})();