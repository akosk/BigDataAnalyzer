/**
 * Created by Ákos on 2015.11.23..
 */


(function () {
    "use strict";

    angular.module('app')
        .factory('configFactoryService', configFactoryService);

    function configFactoryService() {

        var _config = {
            lezeresKozetModell: getLezeresKozetModellConfig(),
            cementesKozetModell: getCementesKozetModellConfig(),
        };


        return _config[MODEL];
    }

    function getLezeresKozetModellConfig() {

        return {
            name: "Lézeres kőzetmodell",
            apiName: "lezeres-kozet-modells",
            formFieldConfig: getLezeresKozetModellFormFieldsConfig(),
            gridConfig: getLezeresKozetModellGridConfig(),
            formatter: lezeresKozetModellFormatVerification
        };

    }

    function getCementesKozetModellConfig() {

        return {
            name: "Cementes kőzetmodell",
            apiName: "cementes-kozet-modells",
            formFieldConfig: getCementesKozetModellFormFieldsConfig(),
            gridConfig: getCementesKozetModellGridConfig(),
            formatter: cementesKozetModellFormatVerification
        };

    }

    function lezeresKozetModellFormatVerification(km) {
        km.belso_kozetmag_keszitesi_datum = moment(km.belso_kozetmag_keszitesi_datum).toISOString();
        km.belso_furat_keszitesi_datum = moment(km.belso_furat_keszitesi_datum).toISOString();
        km.lezeres_befuras_keszitesi_datum = moment(km.lezeres_befuras_keszitesi_datum).toISOString();
        km.kulso_kozetkopeny_keszitesi_datum = moment(km.kulso_kozetkopeny_keszitesi_datum).toISOString();
        km.updated = moment(km.updated).toISOString();
        km.created = moment(km.created).toISOString();
        km.preselesi_ido = moment(km.preselesi_ido, 'HH:mm:ss').format('HH:mm:ss');
        return km;
    }

    function cementesKozetModellFormatVerification(km) {
        km.belso_kozetmag_keszitesi_datum = moment(km.belso_kozetmag_keszitesi_datum).toISOString();
        km.belso_furat_keszitesi_datum = moment(km.belso_furat_keszitesi_datum).toISOString();
        km.cementpalast_keszitesi_datum = moment(km.cementpalast_keszitesi_datum).toISOString();
        km.kulso_kozetkopeny_keszitesi_datum = moment(km.kulso_kozetkopeny_keszitesi_datum).toISOString();
        km.updated = moment(km.updated).toISOString();
        km.created = moment(km.created).toISOString();
        km.preselesi_ido = moment(km.preselesi_ido, 'HH:mm:ss').format('HH:mm:ss');
        return km;
    }

    function getLezeresKozetModellGridConfig() {
        return [
            {id: "id", name: "Id"},
            {id: "kozetmodell_kod", name: "Kőzetmodell kód"},
            {id: "lezeres_befurasi_forma", name: "Lézeres befúrási forma"},
            {id: "kulso_kozetkopeny_atmero", name: "Külső kőzetköpeny átmerő"},
            {id: "preseles_keszito_neve", name: "Préselés készítő neve"},
            {id: "created", name: "Létrehozás ideje"},
            {id: "updated", name: "Módosítás ideje"},
        ];
    }

    function getCementesKozetModellGridConfig() {
        return [
            {id: "id", name: "Id"},
            {id: "kozetmodell_kod", name: "Kőzetmodell kód"},
            {id: "kulso_kozetkopeny_atmero", name: "Külső kőzetköpeny átmérő"},
            {id: "acelcso_kulso_atmero", name: "Acélcső külső átmérő"},
            {id: "preseles_keszito_neve", name: "Préselés készítő neve"},
            {id: "created", name: "Létrehozás ideje"},
            {id: "updated", name: "Módosítás ideje"},
        ];
    }

    function getCementesKozetModellFormFieldsConfig() {
        var part1 = [
            {
                key: 'kozetmodell_kod',
                type: 'maskedInput',
                templateOptions: {
                    label: 'Kőzetmodell kód',
                    mask: '@99@99@@99'
                }
            },
            {
                key: 'kulso_kozetkopeny_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Külső kőzetköpeny átmérő (mm)',
                    min: 25,
                    max: 100,
                    placeholder: 'Adja meg a külső kőzetköpeny átmérőt mm-ben 25 és 100 között...',
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=25 && $viewValue<=100',
                    }
                }
            },
            {
                key: 'belso_kozetmag_kulso_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Belső kőzetmag külső átmérő (mm)',
                    min: 25,
                    max: 100,

                    placeholder: 'Adja meg a belső kőzetmag külső átmérőt mm-ben 25 és 100 között...',
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=25 && $viewValue<=100',
                    }
                }
            },
            {
                key: 'belso_furat_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Belső furat átmérő (mm)',
                    min: 0,
                    max: 10,

                    placeholder: 'Adja meg a belső furat átmérőt mm-ben 0 és 10 között...',
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=10',
                    }
                }
            },
            {
                key: 'acelcso_kulso_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Acélcső külső átmérő (mm)',
                    min: 0,
                    max: 40,

                    placeholder: 'Adja meg az acélcső külső átmérőt mm-ben 0 és 40 között...',
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=40',
                    }
                }
            },
            {
                key: 'acelcso_belso_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Acélcső belső átmérő (mm)',
                    min: 0,
                    max: 40,

                    placeholder: 'Adja meg az acélcső belső átmérőt mm-ben 0 és 40 között...',
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=40',
                    }
                }
            },
            {
                key: 'acelcso_kod',
                type: 'maskedInput',
                templateOptions: {
                    label: 'Acélcső kód',
                    mask: '****'
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
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=40 && $viewValue<=100',
                    }
                }
            },


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
                        //required: true
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
                    //required: true
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
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=40',
                    }
                }
            }

        ];


        for (var i = 1; i <= 12; i++) {
            var postfix = i < 10 ? "0" + i : i;
            part2.push(
                {
                    key: 'cement_komponens_' + postfix,
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Cement komponens ' + i + '. (%)',
                        placeholder: 'Adja meg a cement komponenst 0 és 100% között...',
                        min: 0,
                        max: 100,
                        //required: true
                    },
                    validators: {
                        inRange: {
                            expression: '$viewValue>=0 && $viewValue<=100',
                        }
                    }
                }
            )
        }

        var part3 = [
            {
                key: 'belso_kozetmag_keszitesi_datum',
                type: 'datepicker',
                templateOptions: {
                    type: 'text',
                    label: 'Belső kőzetmag készítési dátum',
                    datepickerPopup: "yyyy-MM-dd",
                    //required: true
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
                    //required: true
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
                    max: 80,
                    placeholder: 'Adja meg az préselási hőmértékletet 0 és 80C között...',
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=80',
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
                    //required: true
                },
                validators: {}
            },
            {
                key: 'belso_furat_keszitesi_datum',
                type: 'datepicker',
                templateOptions: {
                    type: 'text',
                    label: 'Belső furat készítési dátum',
                    datepickerPopup: "yyyy-MM-dd",
                    //required: true
                },
                validators: {}
            },
            {
                key: 'belso_furat_keszito_neve',
                type: 'input',
                templateOptions: {
                    type: 'text',
                    label: 'Belső furat készítő neve',
                    placeholder: 'Adja meg a készítő nevét...',
                    //required: true
                },
                validators: {}
            },
            {
                key: 'cementpalast_keszitesi_datum',
                type: 'datepicker',
                templateOptions: {
                    type: 'text',
                    label: 'Cementpalást készítési dátum',
                    datepickerPopup: "yyyy-MM-dd",
                    //required: true
                },
                validators: {}
            },
            {
                key: 'cementpalast_keszito_neve',
                type: 'input',
                templateOptions: {
                    type: 'text',
                    label: 'Cementpalást készítő neve',
                    placeholder: 'Adja meg a készítő nevét...',
                    //required: true
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
                    //required: true
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
                    //required: true
                },
                validators: {}
            }


        ];
        return part1.concat(part2).concat(part3);
    }


    function getLezeresKozetModellFormFieldsConfig() {
        var part1 = [
            {
                key: 'kozetmodell_kod',
                type: 'maskedInput',
                templateOptions: {
                    label: 'Kőzetmodell kód',
                    mask: '@99@99@@99'
                }
            },
            {
                key: 'kulso_kozetkopeny_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Külső kőzetköpeny átmérő (mm)',
                    min: 25,
                    max: 100,
                    placeholder: 'Adja meg a külső kőzetköpeny átmérőt mm-ben 25 és 100 között...',
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=25 && $viewValue<=100',
                    }
                }
            },
            {
                key: 'belso_kozetmag_kulso_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Belső kőzetmag külső átmérő (mm)',
                    min: 25,
                    max: 100,

                    placeholder: 'Adja meg a belső kőzetmag külső átmérőt mm-ben 25 és 100 között...',
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=25 && $viewValue<=100',
                    }
                }
            },
            {
                key: 'belso_furat_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Belső furat átmérő (mm)',
                    min: 0,
                    max: 10,

                    placeholder: 'Adja meg a belső furat átmérőt mm-ben 0 és 10 között...',
                    //required: true
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
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=40 && $viewValue<=100',
                    }
                }
            },
            {
                key: 'lezeres_furat_atmero',
                type: 'input',
                templateOptions: {
                    type: 'numeric',
                    label: 'Lézeres furat átmérő (mm)',
                    min: 0,
                    max: 10,

                    placeholder: 'Adja meg a lézeres furat átmérőt m-ben 0 és 10 között...',
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=10',
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
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=10 && $viewValue<=20',
                    }
                }
            },
            {
                key: 'lezeres_befurasi_forma',
                type: 'radio',
                wrapper: [],
                templateOptions: {
                    wrapper: [],
                    label: 'Lézeres befúrási forma',
                    //required: true,
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
                            "name": "4A",
                            "value": "4A"
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
                        //required: true
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
                    //required: true
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
                    //required: true
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
                    //required: true
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
                    //required: true
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
                    max: 80,
                    placeholder: 'Adja meg az préselási hőmértékletet 0 és 80C között...',
                    //required: true
                },
                validators: {
                    inRange: {
                        expression: '$viewValue>=0 && $viewValue<=80',
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
                    //required: true
                },
                validators: {}
            },
            {
                key: 'belso_furat_keszitesi_datum',
                type: 'datepicker',
                templateOptions: {
                    type: 'text',
                    label: 'Belső furat készítési dátum',
                    datepickerPopup: "yyyy-MM-dd",
                    //required: true
                },
                validators: {}
            },
            {
                key: 'belso_furat_keszito_neve',
                type: 'input',
                templateOptions: {
                    type: 'text',
                    label: 'Belső furat készítő neve',
                    placeholder: 'Adja meg a készítő nevét...',
                    //required: true
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
                    //required: true
                },
                validators: {}
            },
            {
                key: 'lezeres_befuras_keszito_neve',
                type: 'input',
                templateOptions: {
                    type: 'text',
                    label: 'Lézeres befúrás készítő neve',
                    placeholder: 'Adja meg a készítő nevét...',
                    //required: true
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
                    //required: true
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
                    //required: true
                },
                validators: {}
            }


        ];
        return part1.concat(part2);
    }
})
();