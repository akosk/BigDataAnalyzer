/**
 * Created by Ákos on 2015.06.22..
 */

(function () {
    "use strict";

    angular.module('app')
        .controller('DataManagerController', DataManagerController);


    function DataManagerController($scope, $route, dataService, dragulaService, kozetModellsService) {

        $scope.isBusy = false;
        $scope.dataService = dataService;

        $scope.kozetModell = getDefaultKozetModell();
        $scope.kozetModellFields = getKozetModellFieldConfig();


        $scope.submit = submit;

        $scope.queryAll = kozetModellsService.query().$promise.then(
            function (kozetModellCollection) {
                kozetModellCollection.forEach(function (kozetModell) {

                });
            }
        );

        $scope.save = function () {
            var kozetModell = new kozetModellsService();
                debugger;

            for (var attrname in $scope.kozetModell) { kozetModell[attrname] = $scope.kozetModell[attrname]; }

            //kozetModell = $.extend({}, kozetModell, $scope.kozetModell);

            kozetModellsService.save(kozetModell, function () {
                console.log('hahó');
            });
        };

        function submit() {
            console.log('save...');
            $scope.save();
        }


        $('#spinner').hide();
    }


    function getKozetModellFieldConfig() {
        var part1 = [
            {
                key: 'kozetmodell_kod',
                type: 'maskedInput',
                templateOptions: {
                    label: 'Kőzetmodell kód',
                    mask: '@99@99'
                }
            },
            {
                key: 'kulso_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Külső átmérő (mm)',
                    min: 25,
                    max: 100,
                    placeholder: 'Adja meg a külső átmérőt mm-ben 25 és 100 között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=25 && $viewValue<=100',
                    }
                }
            },
            {
                key: 'belso_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Belső kőzetmag átmérő (mm)',
                    min: 0,
                    max: 10,

                    placeholder: 'Adja meg a belső átmérőt mm-ben 0 és 10 között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=10',
                    }
                }
            },
            {
                key: 'hossz',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Hossz (mm)',
                    min: 40,
                    max: 100,
                    placeholder: 'Adja meg a hosszt mm-ben 40 és 100 között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=40 && $viewValue<=100',
                    }
                }
            },
            {
                key: 'befurasi_melyseg',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Befúrási mélység (m)',
                    min: 10,
                    max: 20,

                    placeholder: 'Adja meg a befúrási mélységet m-ben 10 és 20 között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=10 && $viewValue<=20',
                    }
                }
            },
            {
                key: 'furasi_forma',
                type: 'radio',
                wrapper: [],
                templateOptions: {
                    wrapper: [],
                    label: 'Fúrási forma',
                    required: true,
                    "options": [
                        {
                            "name": "2S",
                            "value": "2S"
                        },
                        {
                            "name": "4S",
                            "value": "4S"
                        },
                        {
                            "name": "4E",
                            "value": "4E"
                        }
                    ]
                }
            }

        ];

        for (var i = 1; i <= 12; i++) {
            part1.push(
                {
                    key: 'homok_frakcio_' + i,
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Homok frakció ' + i + '. (%)',
                        placeholder: 'Adja meg a homok frakciót 0 és 100% között...',
                        min: 0,
                        max: 100,
                        required: true
                    },
                    validators: {
                        inRange: {
                            expression: '$viewValue>=0 && $viewValue<=100',
                        }
                    }
                }
            )
        }

        var part2 = [
            {
                key: 'anyag_frakcio_1',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Anyag frakció 1. (%)',
                    placeholder: 'Adja meg az anyag frakciót 0 és 40% között...',
                    min: 0,
                    max: 40,
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=40',
                    }
                }
            },
            {
                key: 'anyag_frakcio_2',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Anyag frakció 2. (%)',
                    placeholder: 'Adja meg az anyag frakciót 0 és 40% között...',
                    min: 0,
                    max: 40,
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=40',
                    }
                }
            },
            {
                key: 'belso_kozetmag_keszitesi_datum',
                type: 'datepicker',
                templateOptions: {
                    type: 'text',
                    label: 'Belső kőzetmag készítési dátum',
                    datepickerPopup: "yyyy-MM-dd",
                    required: true
                },
                validators: {}
            },
            {
                key: 'preselesi_nyomas',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Préselési nyomás (bar)',
                    min: 0,
                    max: 100,
                    placeholder: 'Adja meg az préselási nyomást 0 és 100bar között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=100',
                    }
                }
            },
            {
                key: 'preselesi_homerseklet',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Préselési hőmérséklet (C)',
                    min: 0,
                    max: 40,
                    placeholder: 'Adja meg az préselási hőmértékletet 0 és 40C között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=40',
                    }
                }
            },
            {
                key: 'preselesi_ido',
                type: 'maskedInput',
                templateOptions: {
                    label: 'Préselési idő',
                    mask: '99:99',
                    placeholder: '00:00'
                }
            },
            {
                key: 'preseles_keszito_neve',
                type: 'input',
                templateOptions: {
                    type: 'text',
                    label: 'Készítő neve',
                    placeholder: 'Adja meg a készítő nevét...',
                    required: true
                },
                validators: {}
            },
            {
                key: 'belso_atmero_keszitesi_datum',
                type: 'datepicker',
                templateOptions: {
                    type: 'text',
                    label: 'Belső átmérő készítési dátum',
                    datepickerPopup: "yyyy-MM-dd",
                    required: true
                },
                validators: {}
            },
            {
                key: 'belso_atmero_keszito_neve',
                type: 'input',
                templateOptions: {
                    type: 'text',
                    label: 'Készítő neve',
                    placeholder: 'Adja meg a készítő nevét...',
                    required: true
                },
                validators: {}
            },
            {
                key: 'lezeres_befuras_keszitesi_datum',
                type: 'datepicker',
                templateOptions: {
                    type: 'text',
                    label: 'Lézeres befúrás készítési dátum',
                    datepickerPopup: "yyyy-MM-dd",
                    required: true
                },
                validators: {}
            },
            {
                key: 'lezeres_befuras_keszito_neve',
                type: 'input',
                templateOptions: {
                    type: 'text',
                    label: 'Készítő neve',
                    placeholder: 'Adja meg a készítő nevét...',
                    required: true
                },
                validators: {}
            },
            {
                key: 'kulso_kozetkopeny_keszitesi_datum',
                type: 'datepicker',
                templateOptions: {
                    type: 'text',
                    label: 'Külső kőzetköpeny készítési dátum',
                    datepickerPopup: "yyyy-MM-dd",
                    required: true
                },
                validators: {}
            },
            {
                key: 'kulso_kozetkopeny_keszito_neve',
                type: 'input',
                templateOptions: {
                    type: 'text',
                    label: 'Készítő neve',
                    placeholder: 'Adja meg a készítő nevét...',
                    required: true
                },
                validators: {}
            }


        ];
        return part1.concat(part2);
    }

    function getDefaultKozetModell() {
        return {
            "kulso_kozetkopeny_keszito_neve": "kkk llll",
            "kozetmodell_kod": "a12b34",
            "kulso_atmero": "25",
            "belso_atmero": "4",
            "hossz": "45",
            "befurasi_melyseg": "11",
            "furasi_forma": "2S",
            "homok_frakcio_1": "3",
            "homok_frakcio_2": "4",
            "homok_frakcio_3": "4",
            "homok_frakcio_4": "4",
            "homok_frakcio_5": "4",
            "homok_frakcio_6": "4",
            "homok_frakcio_7": "4",
            "homok_frakcio_8": "4",
            "homok_frakcio_9": "4",
            "homok_frakcio_10": "4",
            "homok_frakcio_11": "4",
            "homok_frakcio_12": "4",
            "anyag_frakcio_1": "4",
            "anyag_frakcio_2": "4",
            "belso_kozetmag_keszitesi_datum": "2015-11-04T23:00:00.000Z",
            "preselesi_nyomas": "4",
            "preselesi_homerseklet": "4",
            "preselesi_ido": "1000",
            "preseles_keszito_neve": "aaa bbb",
            "belso_atmero_keszitesi_datum": "2015-11-05",
            "belso_atmero_keszito_neve": "ccc ddd",
            "lezeres_befuras_keszitesi_datum": "2015-11-05T23:00:00.000Z",
            "lezeres_befuras_keszito_neve": "hhh jjj",
            "kulso_kozetkopeny_keszitesi_datum": "2015-11-05T23:00:00.000Z"
        };
    }

})();