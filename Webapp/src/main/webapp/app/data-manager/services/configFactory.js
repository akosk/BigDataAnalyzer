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
                namePlural: "Elemzések",
                apiName: "jobs",
                formFieldConfig: getJobFormFieldsConfig(),
                gridConfig: getJobGridConfig(),
                formatter: jobFormatVerification
            };
        }

        function getUserConfig() {
            return {
                name: "Felhasználó",
                namePlural: "Felhasználók",
                apiName: "users",
                formFieldConfig: getUserFormFieldsConfig(),
                gridConfig: getUserGridConfig(),
                formatter: userFormatVerification
            };
        }

        function getMeresiKorulmenyConfig() {
            return {
                name: "Laboratóriumi kísérleti körülmény adat",
                namePlural: "Laboratóriumi kísérleti körülmény adatok",
                apiName: "meresi-korulmenys",
                formFieldConfig: getMeresiKorulmenyFormFieldsConfig(),
                gridConfig: getMeresiKorulmenyGridConfig(),
                formatter: meresiKorulmenyFormatVerification
            };
        }

        function getMeresiEredmenyLezerConfig() {
            return {
                name: "Laboratóriumi mérési eredmény adat",
                namePlural: "Laboratóriumi mérési eredmény adatok",
                apiName: "meresi-eredmeny-lezers",
                formFieldConfig: getMeresiEredmenyLezerFormFieldsConfig(),
                gridConfig: getMeresiEredmenyLezerGridConfig(),
                formatter: meresiEredmenyLezerFormatVerification
            };
        }

        function getMeresiEredmenyCementConfig() {
            return {
                name: "Laboratóriumi mérési eredmény adat",
                namePlural: "Laboratóriumi mérési eredmény adatok",
                apiName: "meresi-eredmeny-cements",
                formFieldConfig: getMeresiEredmenyCementFormFieldsConfig(),
                gridConfig: getMeresiEredmenyCementGridConfig(),
                formatter: meresiEredmenyCementFormatVerification
            };
        }

        function getFluidumConfig() {
            return {
                name: "Fluidum adat",
                namePlural: "Fluidum adatok",
                apiName: "fluidums",
                formFieldConfig: getFluidumFormFieldsConfig(),
                gridConfig: getFluidumGridConfig(),
                formatter: fluidumFormatVerification
            };
        }

        function getLezeresKozetModellConfig() {

            return {
                name: "Lézeres kőzetmodell adat",
                namePlural: "Lézeres kőzetmodell adatok",
                apiName: "lezeres-kozet-modells",
                formFieldConfig: getLezeresKozetModellFormFieldsConfig(),
                gridConfig: getLezeresKozetModellGridConfig(),
                formatter: lezeresKozetModellFormatVerification
            };

        }

        function getCementesKozetModellConfig() {

            return {
                name: "Cementes kőzetmodell adat",
                namePlural: "Cementes kőzetmodell adatok",
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
            if (km.preselesi_ido) {
                km.preselesi_ido = moment(km.preselesi_ido, 'HH:mm:ss').format('HH:mm:ss');
            }
            return km;
        }

        function cementesKozetModellFormatVerification(km) {
            km.belso_kozetmag_keszitesi_datum = moment(km.belso_kozetmag_keszitesi_datum).toISOString();
            km.belso_furat_keszitesi_datum = moment(km.belso_furat_keszitesi_datum).toISOString();
            km.cementpalast_keszitesi_datum = moment(km.cementpalast_keszitesi_datum).toISOString();
            km.kulso_kozetkopeny_keszitesi_datum = moment(km.kulso_kozetkopeny_keszitesi_datum).toISOString();
            km.updated = moment(km.updated).toISOString();
            km.created = moment(km.created).toISOString();
            if (km.preselesi_ido) {
                km.preselesi_ido = moment(km.preselesi_ido, 'HH:mm:ss').format('HH:mm:ss');
            }
            return km;
        }

        function fluidumFormatVerification(km) {
            km.fluidum_keszitesi_ideje=moment(km.fluidum_keszitesi_ideje).toISOString();
            //km.meres_kezdesi_ideje = moment(km.meres_kezdesi_ideje).toISOString();
            //km.meres_befejezesi_ideje = moment(km.meres_befejezesi_ideje).toISOString();
            km.meresi_kezdesi_ideje = moment(km.meresi_kezdesi_ideje).toISOString();
            km.meresi_befejezesi_ideje = moment(km.meresi_befejezesi_ideje).toISOString();
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
                            debugger;


                            $('.modal-grid .panel-body').html(
                                "<div class='panel panel-primary'>" +
                                "<div class='panel-heading'>Elemzés megtekintése</div>" +
                                "<div class='panel-body'>" +
                                "<table class='table table-bordered table-condensed table-striped'" +
                                "<tr><td style='width:30%'>Elemzés konfigurációja:</td><td>" + item.calculation_configuration_name + "</td></tr>" +
                                "<tr><td>Elemzés kezdete:</td><td>" + item.job_start + "</td></tr>" +
                                "<tr><td>Elemzés vége:</td><td>" + item.job_end + "</td></tr>" +
                                "<tr><td>Elemzés kimenete:</td><td>" + item.spark_output + "</td></tr>" +
                                "</table>" +
                                "</div>" +
                                "</div>"
                            );
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
                        mask: 'w99w-99-ww-99',
                        required: true
                    }
                },

                {
                    key: 'kulso_kozetkopeny_atmero',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Külső kőzetköpeny átmérő (mm)',
                        min: 25,
                        max: 100,
                    }
                },
                {
                    key: 'belso_kozetmag_kulso_atmero',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Belső kőzetmag külső átmérő (mm)',
                        min: 25,
                        max: 100,
                    }
                },
                {
                    key: 'belso_furat_atmero',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Belső furat átmérő (mm)',
                        min: 0,
                        max: 10,
                    }
                },
                {
                    key: 'acelcso_kulso_atmero',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Acélcső külső átmérő (mm)',
                        min: 0,
                        max: 40,
                    }
                },
                {
                    key: 'acelcso_belso_atmero',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Acélcső belső átmérő (mm)',
                        min: 0,
                        max: 40,
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
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Hossz (mm)',
                        min: 40,
                        max: 100,
                    }
                },


            ];

            for (var i = 1; i <= 12; i++) {
                part1.push(
                    {
                        key: 'homok_frakcio_' + i,
                        type: 'rangeInput',
                        templateOptions: {
                            label: 'Homok frakció ' + i + '. (%)',
                            min: 0,
                            max: 100,
                        }
                    }
                )
            }
            var part2 = [
                {
                    key: 'anyag_frakcio_1',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Anyag frakció 1. (%)',
                        min: 0,
                        max: 40,
                    }
                },
                {
                    key: 'anyag_frakcio_2',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Anyag frakció 2. (%)',
                        min: 0,
                        max: 40,
                    }
                }

            ];


            for (var i = 1; i <= 12; i++) {
                var postfix = i < 10 ? "0" + i : i;
                part2.push(
                    {
                        key: 'cement_komponens_' + postfix,
                        type: 'rangeInput',
                        templateOptions: {
                            label: 'Cement komponens ' + i + '. (%)',
                            min: 0,
                            max: 100,
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
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Préselési nyomás (bar)',
                        min: 0,
                        max: 100,
                    }
                },
                {
                    key: 'preselesi_homerseklet',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Préselési hőmérséklet (C)',
                        min: 0,
                        max: 80,
                    }
                },
                {
                    key: 'preselesi_ido',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Préselési idő',
                        mask: '99:99:99',
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
                        mask: 'wwww',
                        required: true
                    }
                },
                {
                    key: 'fluidum_megnevezese',
                    type: 'input',
                    templateOptions: {
                        type: 'text',
                        label: 'Fluidum megnevezése',
                        placeholder: 'Adja meg a fluidum nevét...',
                        //required: true
                    },
                    validators: {}
                },
                {
                    key: 'fluidum_keszitesi_ideje',
                    type: 'datetimepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Fluidum készítési ideje',
                    },
                    validators: {}
                },
                {
                    key: 'fluidum_keszitok_nevei',
                    type: 'input',
                    templateOptions: {
                        type: 'text',
                        label: 'Fluidum készítők nevei',
                        placeholder: 'Adja meg a fluidum készítők neveit...',
                        //required: true
                    },
                    validators: {}
                }

            ];

            for (var i = 1; i <= 16; i++) {
                var postfix = i < 10 ? "0" + i : i;
                part1.push(
                    {
                        key: 'fluidum_komponens_' + postfix,
                        type: 'rangeInput',
                        templateOptions: {
                            label: 'Fluidum komponens ' + i + '. (%)',
                            min: 0,
                            max: 100,
                        }
                    }
                )
            }

            part1.push(
                {
                    key: 'korulmeny_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Körülmény kód',
                        mask: 'wwww',
                        required: true
                    }
                },
                {
                    key: 'meresi_kezdesi_ideje',
                    type: 'datetimepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérés kezdési ideje',
                    },
                    validators: {}
                },
                {
                    key: 'meresi_befejezesi_ideje',
                    type: 'datetimepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérés befejezési ideje',
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
                    key: 'suruseg',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Sűrűség kg/dm3',
                        min: 0,
                        max: 2,
                    }
                },
                {
                    key: 'viszkozitas',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Viszkozitás mPa s',
                        min: 0,
                        max: 10,
                    }
                },
                {
                    key: 'fajho',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Fajhő J/(kgK)',
                        min: 0,
                        max: 4200,
                    }
                },
                {
                    key: 'hovezetokepesseg',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Hővezetőképesség W/(mK)',
                        min: 0,
                        max: 2,
                    }
                }
            );

            return part1;
        }

        function getMeresiKorulmenyFormFieldsConfig() {
            var part1 = [
                {
                    key: 'korulmeny_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Körülmény kód',
                        mask: 'wwww',
                        required: true
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
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Hőmérséklet (C)',
                        min: 0,
                        max: 120,
                    }
                },
                {
                    key: 'legkori_nyomas',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Légköri nyomás (bar)',
                        min: 0.8,
                        max: 1.2,
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
                        label: 'Mérés kód',
                        mask: 'wwww-99.99.99-99',
                        required: true
                    }
                },
                {
                    key: 'kozetmodell_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Kőzetmodell kód',
                        mask: 'w99w-99-ww-99',
                        required: true
                    }
                },
                {
                    key: 'fluidum_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Fluidum kód',
                        mask: 'wwww',
                        required: true
                    }
                },
                {
                    key: 'korulmeny_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Körülmény kód',
                        mask: 'wwww',
                        required: true
                    }
                },
                {
                    key: 'meres_kezdesi_ideje',
                    type: 'datetimepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérés kezdési ideje',
                    },
                    validators: {}
                },

                {
                    key: 'meres_befejezesi_ideje',
                    type: 'datetimepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérés befejezési ideje',
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
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Porozitas (%)',
                        min: 0,
                        max: 80,
                    }
                },
                {
                    key: 'permeabilitas',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Permeabilitás mD',
                        min: 0,
                        max: 10000,
                    }
                },
                {
                    key: 'suruseg',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Sűrűség kg/m3',
                        min: 0,
                        max: 4,
                    }
                },
                {
                    key: 'fajho',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Fajhő J/(kgK)',
                        min: 0,
                        max: 4200,
                    }
                },
                {
                    key: 'hovezetokepesseg',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Hővezetőképesség W/(mK)',
                        min: 0,
                        max: 8,
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
                        mask: 'w99w-99-ww-99',
                        required: true
                    }
                },
                {
                    key: 'kulso_kozetkopeny_atmero',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Külső kőzetköpeny átmérő (mm)',
                        min: 25,
                        max: 100,
                    }
                },
                {
                    key: 'belso_kozetmag_kulso_atmero',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Belső kőzetmag külső átmérő (mm)',
                        min: 25,
                        max: 100,
                    }
                },
                {
                    key: 'belso_furat_atmero',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Belső furat átmérő (mm)',
                        min: 0,
                        max: 10,
                    }
                },
                {
                    key: 'hossz',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Hossz (mm)',
                        min: 40,
                        max: 100,
                    }
                },
                {
                    key: 'lezeres_furat_atmero',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Lézeres furat átmérő (mm)',
                        min: 0,
                        max: 10,
                    }
                },
                {
                    key: 'befurasi_melyseg',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Befúrási mélység (m)',
                        min: 10,
                        max: 20,
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
                        type: 'rangeInput',
                        templateOptions: {
                            label: 'Homok frakció ' + i + '. (%)',
                            min: 0,
                            max: 100,
                        }
                    }
                )
            }

            var part2 = [
                {
                    key: 'anyag_frakcio_1',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Anyag frakció 1. (%)',
                        min: 0,
                        max: 40,
                    }
                },
                {
                    key: 'anyag_frakcio_2',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Anyag frakció 2. (%)',
                        min: 0,
                        max: 40,
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
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Préselési nyomás (bar)',
                        min: 0,
                        max: 100,
                    }
                },
                {
                    key: 'preselesi_homerseklet',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Préselési hőmérséklet (C)',
                        min: 0,
                        max: 80,
                    }
                },
                {
                    key: 'preselesi_ido',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Préselési idő',
                        mask: '99:99:99',
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
                        mask: '99-99-99-99-99.99.99-99',
                        required: true
                    }
                },
                {
                    key: 'kozetmodell_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Kőzetmodell kód',
                        mask: 'wwwwwwww',
                        required: true
                    }
                },
                {
                    key: 'fluidum_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Fluidum kód',
                        mask: 'wwww',
                        required: true
                    }
                },
                {
                    key: 'korulmeny_kod',
                    type: 'maskedInput',
                    templateOptions: {
                        label: 'Körülmény kód',
                        mask: 'wwww',
                        required: true
                    }
                },
                {
                    key: 'meres_kezdesi_ideje',
                    type: 'datetimepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérés kezdési ideje',
                    },
                    validators: {}
                },
                {
                    key: 'meres_befejezesi_ideje',
                    type: 'datetimepicker',
                    templateOptions: {
                        type: 'text',
                        label: 'Mérés befejezési ideje',
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
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Cső hővezetőképessége W/(mk)',
                        min: 20,
                        max: 80
                    }
                },
                {
                    key: 'cementpalast_hovezetokepessege',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Cementpalást hővezetőképessége W/(mk)',
                        min: 0,
                        max: 1
                    }
                },
                {
                    key: 'kozet_hovezetokepessege',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Kőzet hővezetőképessége W/(mk)',
                        min: 0,
                        max: 2
                    }
                },
                {
                    key: 'csofluidum_hovezetokepessege',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Csőfluidum hővezetőképessége W/(mk)',
                        min: 0,
                        max: 1
                    }
                },
                {
                    key: 'kozetfluidum_hovezetokepessege',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Kőzetfluidum hővezetőképessége W/(mk)',
                        min: 0,
                        max: 1
                    }
                },
                {
                    key: 'csofluidum_sebessege',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Csőfluidum sebessége m/s',
                        min: 0,
                        max: 4
                    }
                },
                {
                    key: 'kozetfluidum_sebessege',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Kőzetfluidum sebessége m/s',
                        min: 0,
                        max: 4
                    }
                },
                {
                    key: 'csofluidum_belepo_homerseklete',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Csőfluidum belépő hőmérséklete C',
                        min: 0,
                        max: 120
                    }
                },
                {
                    key: 'csofluidum_kilepo_homerseklete',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Csőfluidum kilépő hőmérséklete C',
                        min: 0,
                        max: 120
                    }
                },
                {
                    key: 'csofluidum_belepo_nyomasa',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Csőfluidum belépő nyomása bar',
                        min: 0.8,
                        max: 10
                    }
                },
                {
                    key: 'csofluidum_kilepo_nyomasa',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Csőfluidum kilépő nyomása bar',
                        min: 0.8,
                        max: 10
                    }
                },
                {
                    key: 'kozetfluidum_belepo_homerseklete',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Kőzetfluidum belépő hőmérséklete C',
                        min: 0,
                        max: 120
                    }
                },
                {
                    key: 'kozetfluidum_kilepo_homerseklete',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Kőzetfluidum kilépő hőmérséklete C',
                        min: 0,
                        max: 120
                    }
                },
                {
                    key: 'kozetfluidum_belepo_nyomasa',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Kőzetfluidum belépő nyomása bar',
                        min: 0.8,
                        max: 10
                    }
                },
                {
                    key: 'kozetfluidum_kilepo_nyomasa',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Kőzetfluidum kilépő nyomása bar',
                        min: 0.8,
                        max: 10
                    }
                },
                {
                    key: 'ultrahang_jelzes_ideje',
                    type: 'rangeInput',
                    templateOptions: {
                        label: 'Ultrahang jelzés ideje',
                        min: 0,
                        max: 8000
                    },
                    validators: {}
                }


            ];


            return part1;
        }


        function getJobFormFieldsConfig() {

            var config = [

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

            return config;
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
                    templateOptions: {label: 'Engedélyezve'},
                }

            ];


            return part1;
        }


    }
})
();