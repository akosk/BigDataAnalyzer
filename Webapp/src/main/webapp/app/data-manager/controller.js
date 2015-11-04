/**
 * Created by Ákos on 2015.06.22..
 */

(function () {
    "use strict";

    angular.module('app')
        .controller('DataManagerController', DataManagerController);


    function DataManagerController($scope, $route, dataService, dragulaService) {

        $scope.isBusy = false;
        $scope.dataService = dataService;

        $scope.user = {};
        $scope.userFields = getUserFieldConfig();


        $('#spinner').hide();
    }


    function getUserFieldConfig() {
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
                    placeholder: 'Adja meg a külső átmérőt mm-ben 25 és 100 között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=25 && $viewValue<=100',
                        message: 'Az értéknek 25 és 100 közé kell esnie.'
                    }
                }
            },
            {
                key: 'belso_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Belső kőzetmag átmérő (mm)',
                    placeholder: 'Adja meg a belső átmérőt mm-ben 0 és 10 között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=10',
                        message: 'Az értéknek 0 és 10 közé kell esnie.'
                    }
                }
            },
            {
                key: 'hossz',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Hossz (mm)',
                    placeholder: 'Adja meg a hosszt mm-ben 40 és 100 között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=40 && $viewValue<=100',
                        message: 'Az értéknek 40 és 100 közé kell esnie.'
                    }
                }
            },
            {
                key: 'befurasi_melyseg',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Befúrási mélység (m)',
                    placeholder: 'Adja meg a befúrási mélységet m-ben 10 és 20 között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=10 && $viewValue<=20',
                        message: 'Az értéknek 10 és 20 közé kell esnie.'
                    }
                }
            },
            {
                key: 'furasi_forma',
                type: 'radio',
                templateOptions: {
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
                        required: true
                    },
                    validators: {
                        inRange: {
                            expression: '$viewValue>=0 && $viewValue<=100',
                            message: 'Az értéknek 0 és 100 közé kell esnie.'
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
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=40',
                        message: 'Az értéknek 0 és 40 közé kell esnie.'
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
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=40',
                        message: 'Az értéknek 0 és 40 közé kell esnie.'
                    }
                }
            },
            {
                key: 'belso_kozetmag_keszitesi_datum',
                type: 'datepicker',
                templateOptions: {
                    type: 'text',
                    label: 'Belső kőzetmag készítési dátum',
                    "datepickerPopup": "yyyy-MMMM-dd",
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
                    placeholder: 'Adja meg az préselási nyomást 0 és 100bar között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=100',
                        message: 'Az értéknek 0 és 100 közé kell esnie.'
                    }
                }
            },
            {
                key: 'preselesi_homerseklet',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Préselési hőmérséklet (C)',
                    placeholder: 'Adja meg az préselási hőmértékletet 0 és 40C között...',
                    required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=40',
                        message: 'Az értéknek 0 és 40 közé kell esnie.'
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
                    "datepickerPopup": "yyyy-MMMM-dd",
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
                    "datepickerPopup": "yyyy-MMMM-dd",
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
                    "datepickerPopup": "yyyy-MMMM-dd",
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

})();