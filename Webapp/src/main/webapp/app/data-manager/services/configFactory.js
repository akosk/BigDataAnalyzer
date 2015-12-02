/**
 * Created by Ákos on 2015.11.23..
 */


(function () {
    "use strict";

    angular.module('app')
        .factory('configFactoryService', configFactoryService);

    function configFactoryService($http) {

        var _config = {
            lezeresKozetModell: getLezeresKozetModellConfig(),
            cementesKozetModell: getCementesKozetModellConfig(),
            fluidum: getFluidumConfig(),
            meresiKorulmeny: getMeresiKorulmenyConfig(),
            meresiEredmenyLezer: getMeresiEredmenyLezerConfig(),
            meresiEredmenyCement: getMeresiEredmenyCementConfig(),
            job: getJobConfig(),
            user: getUserConfig()
        };


        return _config[MODEL];


        function getJobConfig() {
            return {
                name: "Elemzés",
                apiName: "jobs",
                formFieldConfig: getJobFormFieldsConfig(),
                gridConfig: getJobGridConfig(),
                formatter: jobFormatVerification
            };
        }

        function getUserConfig() {
            return {
                name: "Felhasználó",
                apiName: "users",
                formFieldConfig: getUserFormFieldsConfig(),
                gridConfig: getUserGridConfig(),
                formatter: userFormatVerification
            };
        }

        function getMeresiKorulmenyConfig() {
            return {
                name: "Mérési körülmény",
                apiName: "meresi-korulmenys",
                formFieldConfig: getMeresiKorulmenyFormFieldsConfig(),
                gridConfig: getMeresiKorulmenyGridConfig(),
                formatter: meresiKorulmenyFormatVerification
            };
        }

        function getMeresiEredmenyLezerConfig() {
            return {
                name: "Mérési eredmény",
                apiName: "meresi-eredmeny-lezers",
                formFieldConfig: getMeresiEredmenyLezerFormFieldsConfig(),
                gridConfig: getMeresiEredmenyLezerGridConfig(),
                formatter: meresiEredmenyLezerFormatVerification
            };
        }

        function getMeresiEredmenyCementConfig() {
            return {
                name: "Mérési eredmény",
                apiName: "meresi-eredmeny-cements",
                formFieldConfig: getMeresiEredmenyCementFormFieldsConfig(),
                gridConfig: getMeresiEredmenyCementGridConfig(),
                formatter: meresiEredmenyCementFormatVerification
            };
        }

        function getFluidumConfig() {
            return {
                name: "Fluidum",
                apiName: "fluidums",
                formFieldConfig: getFluidumFormFieldsConfig(),
                gridConfig: getFluidumGridConfig(),
                formatter: fluidumFormatVerification
            };
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

        function userFormatVerification(km) {
            km.updated = moment(km.updated).toISOString();
            km.created = moment(km.created).toISOString();
            return km;
        }

        function jobFormatVerification(km) {
            km.updated = moment(km.updated).toISOString();
            km.created = moment(km.created).toISOString();
            return km;
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

        function fluidumFormatVerification(km) {
            km.updated = moment(km.updated).toISOString();
            km.created = moment(km.created).toISOString();
            return km;
        }

        function meresiKorulmenyFormatVerification(km) {
            km.datum = moment(km.datum).toISOString();
            km.updated = moment(km.updated).toISOString();
            km.created = moment(km.created).toISOString();
            return km;
        }

        function meresiEredmenyLezerFormatVerification(km) {
            km.meres_kezdesi_ideje = moment(km.meres_kezdesi_ideje).toISOString();
            km.meres_befejezesi_ideje = moment(km.meres_befejezesi_ideje).toISOString();
            km.updated = moment(km.updated).toISOString();
            km.created = moment(km.created).toISOString();
            return km;
        }

        function meresiEredmenyCementFormatVerification(km) {
            km.meres_kezdesi_ideje = moment(km.meres_kezdesi_ideje).toISOString();
            km.meres_befejezesi_ideje = moment(km.meres_befejezesi_ideje).toISOString();
            km.ultrahang_jelzes_ideje = moment(km.ultrahang_jelzes_ideje).toISOString();
            km.updated = moment(km.updated).toISOString();
            km.created = moment(km.created).toISOString();
            return km;
        }

        function getMeresiEredmenyLezerGridConfig() {
            return {
                dataColumns: [
                    {id: "id", name: "Id"},
                    {id: "meres_kod", name: "Mérés kód"},
                    {id: "kozetmodell_kod", name: "Kőzetmodell kód"},
                    {id: "fluidum_kod", name: "Fluidum kód"},
                    {id: "korulmeny_kod", name: "Körülmény kód"},
                    {id: "meres_kezdesi_ideje", name: "Mérés kezdési ideje"},
                    {id: "meres_befejezesi_ideje", name: "Mérés befejezési ideje"},
                    {id: "created", name: "Létrehozás ideje"},
                    {id: "updated", name: "Módosítás ideje"},
                ]
            };
        }

        function getJobGridConfig() {
            return {
                dataColumns: [
                    {id: "id", name: "Id"},
                    {id: "calculation_configuration_name", name: "Konfiguráció  "},
                    {id: "job_start", name: "Elemzés kezdete"},
                    {id: "job_end", name: "Elemzés vége"},
                    {id: "status", name: "Státusz"},
                    {id: "created", name: "Létrehozás ideje"},
                ],
                actions: [
                    {
                        name: "Megtekintés",
                        icon: "fa fa-eye",
                        click: function (item) {
                            $('.modal-grid .panel-body').html(item.spark_output);
                            $('.modal-grid').modal("show");
                        }
                    }
                ]
            };
        }

        function getMeresiEredmenyCementGridConfig() {
            return {
                dataColumns: [
                    {id: "id", name: "Id"},
                    {id: "meres_kod", name: "Mérés kód"},
                    {id: "kozetmodell_kod", name: "Kőzetmodell kód"},
                    {id: "fluidum_kod", name: "Fluidum kód"},
                    {id: "korulmeny_kod", name: "Körülmény kód"},
                    {id: "meres_kezdesi_ideje", name: "Mérés kezdési ideje"},
                    {id: "meres_befejezesi_ideje", name: "Mérés befejezési ideje"},
                    {id: "created", name: "Létrehozás ideje"},
                    {id: "updated", name: "Módosítás ideje"},
                ]
            };
        }

        function getUserGridConfig() {
            return {
                dataColumns: [
                    {id: "id", name: "Id"},
                    {id: "username", name: "Felhasználónév"},
                    {id: "password", name: "Jelszó"},
                    {id: "enabled", name: "Engedélyezve"},
                    {id: "created", name: "Létrehozás ideje"},
                    {id: "updated", name: "Módosítás ideje"},
                ]
            };
        }

        function getMeresiKorulmenyGridConfig() {
            return {
                dataColumns: [
                    {id: "id", name: "Id"},
                    {id: "korulmeny_kod", name: "Körülmény kód"},
                    {id: "datum", name: "Dátum"},
                    {id: "created", name: "Létrehozás ideje"},
                    {id: "updated", name: "Módosítás ideje"},
                ]
            };
        }

        function getFluidumGridConfig() {
            return {
                dataColumns: [
                    {id: "id", name: "Id"},
                    {id: "fluidum_kod", name: "Fluidum kód"},
                    {id: "created", name: "Létrehozás ideje"},
                    {id: "updated", name: "Módosítás ideje"},
                ]
            };
        }

        function getLezeresKozetModellGridConfig() {
            return {
                dataColumns: [
                    {id: "id", name: "Id"},
                    {id: "kozetmodell_kod", name: "Kőzetmodell kód"},
                    {id: "lezeres_befurasi_forma", name: "Lézeres befúrási forma"},
                    {id: "kulso_kozetkopeny_atmero", name: "Külső kőzetköpeny átmerő"},
                    {id: "preseles_keszito_neve", name: "Préselés készítő neve"},
                    {id: "created", name: "Létrehozás ideje"},
                    {id: "updated", name: "Módosítás ideje"},
                ]
            };
        }

        function getCementesKozetModellGridConfig() {
            return {
                dataColumns: [
                    {id: "id", name: "Id"},
                    {id: "kozetmodell_kod", name: "Kőzetmodell kód"},
                    {id: "kulso_kozetkopeny_atmero", name: "Külső kőzetköpeny átmérő"},
                    {id: "acelcso_kulso_atmero", name: "Acélcső külső átmérő"},
                    {id: "preseles_keszito_neve", name: "Préselés készítő neve"},
                    {id: "created", name: "Létrehozás ideje"},
                    {id: "updated", name: "Módosítás ideje"},
                ]
            };
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


        function getFluidumFormFieldsConfig() {
            var part1 = [
                {
                    key: 'fluidum_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Fluidum kód',
                        mask: '@@@@'
                    }
                }
            ];

            for (var i = 1; i <= 16; i++) {
                var postfix = i < 10 ? "0" + i : i;
                part1.push(
                    {
                        key: 'fluidum_komponens_' + postfix,
                        type: 'input',
                        templateOptions: {
                            type: 'numeric',
                            label: 'Fluidum komponens ' + i + '. (%)',
                            placeholder: 'Adja meg a fluidum komponenst 0 és 100% között...',
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

            return part1;
        }

        function getMeresiKorulmenyFormFieldsConfig() {
            var part1 = [
                {
                    key: 'korulmeny_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Körülmény kód',
                        mask: '@@@@'
                    }
                },
                {
                    key: 'datum',
                    type: 'datepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Dátum',
                        datepickerPopup: "yyyy-MM-dd",
                        //required: true
                    },
                    validators: {}
                },
                {
                    key: 'helyszin_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Helyszín kód',
                        mask: '@@'
                    }
                },
                {
                    key: 'megvilagitas',
                    type: 'radio',
                    wrapper: [],
                    templateOptions: {
                        wrapper: [],
                        label: 'Megvilágítás',
                        //required: true,
                        "options": [
                            {
                                "name": "T",
                                "value": "T"
                            },
                            {
                                "name": "M",
                                "value": "M"
                            }
                        ]
                    }
                },
                {
                    key: 'homerseklet',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Hőmérséklet (C)',
                        min: 0,
                        max: 120,
                        placeholder: 'Adja meg a hőmérsékletet 0 és 120 között...',
                        //required: true
                    },
                    validators: {
                        inRange: {
                            expression: '$viewValue>=0 && $viewValue<=120',
                        }
                    }
                },
                {
                    key: 'legkori_nyomas',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Légköri nyomás (bar)',
                        min: 0,
                        max: 1200,
                        placeholder: 'Adja meg a légköri nyomást 0 és 1200 között...',
                        //required: true
                    },
                    validators: {
                        inRange: {
                            expression: '$viewValue>=0 && $viewValue<=1200',
                        }
                    }
                }


            ];


            return part1;
        }

        function getMeresiEredmenyLezerFormFieldsConfig() {
            var part1 = [
                {
                    key: 'meres_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Körülmény kód',
                        mask: '@@@@99999999'
                    }
                },
                {
                    key: 'kozetmodell_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Kőzetmodell kód',
                        mask: '@99@99@@99'
                    }
                },
                {
                    key: 'fluidum_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Fluidum kód',
                        mask: '@@@@'
                    }
                },
                {
                    key: 'korulmeny_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Körülmény kód',
                        mask: '@@@@'
                    }
                },
                {
                    key: 'meres_kezdesi_ideje',
                    type: 'datepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérés kezdési ideje',
                        datepickerPopup: "yyyy-MM-dd",
                        //required: true
                    },
                    validators: {}
                },
                {
                    key: 'meres_befejezesi_ideje',
                    type: 'datepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérés befejezési ideje',
                        datepickerPopup: "yyyy-MM-dd",
                        //required: true
                    },
                    validators: {}
                },
                {
                    key: 'merest_vegzok_nevei',
                    type: 'input',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérést végzők nevei',
                        placeholder: 'Adja meg a mérést végzők neveit...',
                        //required: true
                    },
                    validators: {}
                },

                {
                    key: 'porozitas',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Porozitas (%)',
                        min: 0,
                        max: 10,
                        placeholder: 'Adja meg a porozitast 0 és 10 között...',
                        //required: true
                    },
                    validators: {
                        inRange: {
                            expression: '$viewValue>=0 && $viewValue<=10',
                        }
                    }
                },
                {
                    key: 'permeabilitas',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Permeabilitás mD',
                        min: 0,
                        max: 80,
                        placeholder: 'Adja meg a permeabilitást 0 és 80 között...',
                        //required: true
                    },
                    validators: {
                        inRange: {
                            expression: '$viewValue>=0 && $viewValue<=80',
                        }
                    }
                },
                {
                    key: 'hovezetokepesseg',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Hővezetőképesség W/(mK)',
                        min: 0,
                        max: 80,
                        placeholder: 'Adja meg a hővezetőképességet 0 és 200 között...',
                        //required: true
                    },
                    validators: {
                        inRange: {
                            expression: '$viewValue>=0 && $viewValue<=200',
                        }
                    }
                },
                {
                    key: 'viszkozitas',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Viszkozitás Pa s',
                        min: 0,
                        max: 80,
                        placeholder: 'Adja meg a viszkozitás 0 és 400 között...',
                        //required: true
                    },
                    validators: {
                        inRange: {
                            expression: '$viewValue>=0 && $viewValue<=400',
                        }
                    }
                },
                {
                    key: 'suruseg',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Sűrűség kg/m3',
                        min: 0,
                        max: 80,
                        placeholder: 'Adja meg a sűrűséget 0.8 és 4 között...',
                        //required: true
                    },
                    validators: {
                        inRange: {
                            expression: '$viewValue>=0.8 && $viewValue<=4',
                        }
                    }
                }


            ];


            return part1;
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


        function getMeresiEredmenyCementFormFieldsConfig() {
            var part1 = [
                {
                    key: 'meres_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Körülmény kód',
                        mask: '9999999999999999'
                    }
                },
                {
                    key: 'kozetmodell_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Kőzetmodell kód',
                        mask: '@99@99@@99'
                    }
                },
                {
                    key: 'fluidum_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Fluidum kód',
                        mask: '@@@@'
                    }
                },
                {
                    key: 'korulmeny_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Körülmény kód',
                        mask: '@@@@'
                    }
                },
                {
                    key: 'meres_kezdesi_ideje',
                    type: 'datepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérés kezdési ideje',
                        datepickerPopup: "yyyy-MM-dd",
                        //required: true
                    },
                    validators: {}
                },
                {
                    key: 'meres_befejezesi_ideje',
                    type: 'datepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérés befejezési ideje',
                        datepickerPopup: "yyyy-MM-dd",
                        //required: true
                    },
                    validators: {}
                },
                {
                    key: 'merest_vegzok_nevei',
                    type: 'input',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérést végzők nevei',
                        placeholder: 'Adja meg a mérést végzők neveit...',
                        //required: true
                    },
                    validators: {}
                },
                {
                    key: 'cso_hovezetokepessege',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Cső hővezetőképessége W(mk)',
                        placeholder: 'Adja meg a cső hővezetőképességét...',
                        //required: true
                    }
                },
                {
                    key: 'cementpalast_hovezetokepessege',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Cementpalást hővezetőképessége W(mk)',
                        placeholder: 'Adja meg a cementpalást hővezetőképességét...',
                        //required: true
                    }
                },
                {
                    key: 'kozet_hovezetokepessege',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Kőzet hővezetőképessége W(mk)',
                        placeholder: 'Adja meg a kőzet hővezetőképességét...',
                        //required: true
                    }
                },
                {
                    key: 'csofluidum_hovezetokepessege',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Csőfluidum hővezetőképessége W(mk)',
                        placeholder: 'Adja meg a csőfluidum hővezetőképességét...',
                        //required: true
                    }
                },
                {
                    key: 'kozetfluidum_hovezetokepessege',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Kőzetfluidum hővezetőképessége W(mk)',
                        placeholder: 'Adja meg a kőzetfluidum hővezetőképességét...',
                        //required: true
                    }
                },
                {
                    key: 'csofluidum_sebessege',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Csőfluidum sebessége m/S',
                        placeholder: 'Adja meg a csőfluidum sebességét...',
                        //required: true
                    }
                },
                {
                    key: 'kozetfluidum_sebessege',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Kőzetfluidum sebessége m/S',
                        placeholder: 'Adja meg a kőzetfluidum sebességét...',
                        //required: true
                    }
                },
                {
                    key: 'csofluidum_belepo_homerseklete',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Csőfluidum belépő hőmérséklete C',
                        placeholder: 'Adja meg a csőfluidum belépő hőmérsékletét...',
                        //required: true
                    }
                },
                {
                    key: 'csofluidum_kilepo_homerseklete',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Csőfluidum kilépő hőmérséklete C',
                        placeholder: 'Adja meg a csőfluidum kilépő hőmérsékletét...',
                        //required: true
                    }
                },
                {
                    key: 'csofluidum_belepo_nyomasa',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Csőfluidum belépő nyomása bar',
                        placeholder: 'Adja meg a csőfluidum belépő nyomását...',
                        //required: true
                    }
                },
                {
                    key: 'csofluidum_kilepo_nyomasa',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Csőfluidum kilépő nyomása bar',
                        placeholder: 'Adja meg a csőfluidum kilépő nyomását...',
                        //required: true
                    }
                },
                {
                    key: 'kozetfluidum_belepo_homerseklete',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Kőzetfluidum belépő hőmérséklete C',
                        placeholder: 'Adja meg a kőzetfluidum belépő hőmérsékletét...',
                        //required: true
                    }
                },
                {
                    key: 'kozetfluidum_kilepo_homerseklete',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Kőzetfluidum kilépő hőmérséklete C',
                        placeholder: 'Adja meg a kőzetfluidum kilépő hőmérsékletét...',
                        //required: true
                    }
                },
                {
                    key: 'kozetfluidum_belepo_nyomasa',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Kőzetfluidum belépő nyomása bar',
                        placeholder: 'Adja meg a kőzetfluidum belépő nyomását...',
                        //required: true
                    }
                },
                {
                    key: 'kozetfluidum_kilepo_nyomasa',
                    type: 'input',
                    templateOptions: {
                        type: 'numeric',
                        label: 'Kőzetfluidum kilépő nyomása bar',
                        placeholder: 'Adja meg a kőzetfluidum kilépő nyomását...',
                        //required: true
                    }
                },
                {
                    key: 'ultrahang_jelzes_ideje',
                    type: 'datepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Ultrahang jelzés ideje',
                        datepickerPopup: "yyyy-MM-dd",
                        //required: true
                    },
                    validators: {}
                }


            ];


            return part1;
        }


        function getJobFormFieldsConfig() {


            var part1 = [

                {
                    key: 'calculation_configuration_id',
                    type: 'select',
                    wrapper: [],
                    templateOptions: {
                        wrapper: [],
                        label: 'Konfiguráció',
                        options: []
                    },
                    controller: /* @ngInject */ function ($scope) {

                        $http({
                            method: 'GET',
                            url: '/webapi-calculation-configurations-list'
                        }).then(function successCallback(response) {
                            $scope.to.options = response.data;
                        }, function errorCallback(response) {
                        });

                    }
                }
            ];


            return part1;
        }

        function getUserFormFieldsConfig() {


            var part1 = [

                {
                    key: 'username',
                    type: 'input',
                    templateOptions: {
                        type: 'text',
                        label: 'Felhasználó neve',
                        placeholder: 'Adja meg a felhasználó nevét...',
                        required: true
                    },
                    validators: {}
                },
                {
                    key: 'password',
                    type: 'input',
                    templateOptions: {
                        type: 'text',
                        label: 'Felhasználó jelszava',
                        placeholder: 'Adja meg a felhasználó jelszavát...',
                        required: true
                    },
                    validators: {}
                },
                {
                    key: 'role_id',
                    type: 'select',
                    wrapper: [],
                    templateOptions: {
                        wrapper: [],
                        label: 'Szerepkör',
                        options: [
                            {name: "Felhasználó", value: "ROLE_USER"},
                            {name: "Adminisztrátor", value: "ROLE_ADMIN"}
                        ]
                    }
                },
                {
                    key: 'enabled',
                    type: 'checkbox',
                    templateOptions: { label: 'Engedélyezve' },
                }

            ];


            return part1;
        }


    }
})
();