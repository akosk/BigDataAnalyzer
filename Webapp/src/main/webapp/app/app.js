/**
 * Created by Ákos on 2015.06.22..
 */

var VERSION = '1';
var PRIMARY_KEY = PRIMARY_KEY || "id";


(function () {
    "use strict";

    moment.locale('hu-HU');

    var app = angular.module("app", [
        'ngRoute',
        'ngAnimate',
        'ui.bootstrap',
        'ui.bootstrap.datetimepicker',
        'ui.dateTimeInput',
        angularDragula(angular),
        'formly',
        'ngMessages',
        'ngResource',
        'formlyBootstrap',
        'ngMask'], config)
        .run(formlyInit);


    function config(formlyConfigProvider) {

        formlyConfigProvider.setWrapper([
            {name: 'testWrapper', template: '<div>Halló<formly-transclude></formly-transclude>Lajos</div>'}
        ]);

        //formlyConfigProvider.setWrapper([
        //    {
        //        template: [
        //            '<div class="formly-template-wrapper form-group"',
        //            'ng-class="{\'has-error\': options.validation.errorExistsAndShouldBeVisible}">',
        //            //'<label for="{{::id}}">{{options.templateOptions.label}} {{options.templateOptions.required ? \'*\' : \'\'}}</label>',
        //            '<formly-transclude></formly-transclude>',
        //            '<div class="validation"',
        //            'ng-if="options.validation.errorExistsAndShouldBeVisible"',
        //            'ng-messages="options.formControl.$error">',
        //            '<div>Hahó</div>',
        //            '<div ng-messages-include="validation.html"></div>',
        //            '<div ng-message="{{::name}}" ng-repeat="(name, message) in ::options.validation.messages">',
        //            '{{message(options.formControl.$viewValue, options.formControl.$modelValue, this)}}',
        //            '</div>',
        //            '</div>',
        //            '</div>'
        //        ].join(' ')
        //    }
        //
        //]);
    }


    function formlyInit(formlyConfig, formlyValidationMessages) {
        setNgMask(formlyConfig);
        setDatePicker(formlyConfig);
        setDateTimePicker(formlyConfig);
        setSelectXhr(formlyConfig);
        setRangeInput(formlyConfig);

        formlyConfig.extras.ngModelAttrsManipulatorPreferBound = true;


    };

    function camelize(string) {
        string = string.replace(/[\-_\s]+(.)?/g, function (match, chr) {
            return chr ? chr.toUpperCase() : '';
        });
        // Ensure 1st char is always lowercase
        return string.replace(/^([A-Z])/, function (match, chr) {
            return chr ? chr.toLowerCase() : '';
        });
    }

    function setSelectXhr(formlyConfig) {
        formlyConfig.setType({
            name: 'selectXhr',
            extends: 'select',
            wrapper: 'testWrapper'
        });
    }

    function setRangeInput(formlyConfig) {
        formlyConfig.setType({
            name: 'rangeInput',
            extends: 'input',
            defaultOptions: {
                templateOptions: {
                    type: 'numeric',
                },
                validators: {
                    inRange: {
                        expression: function ($viewValue, $modelValue, scope) {
                            return !($viewValue) || ($viewValue >= scope.to.min && $viewValue <= scope.to.max);
                        }
                    }
                },
                controller: /* @ngInject */ function ($scope) {
                    var label = $scope.to.label.charAt(0).toLowerCase() + $scope.to.label.slice(1);
                    $scope.to.placeholder = 'Adja meg a ' + label + ' értékét ' +
                        $scope.to.min + ' és ' + $scope.to.max + ' között...';
                }
            }
        });
    }

    function setNgMask(formlyConfig) {
        formlyConfig.setType({
            name: 'maskedInput',
            extends: 'input',
            defaultOptions: {
                ngModelAttrs: { // this is part of the magic... It's a little complex, but super powerful
                    mask: { // the key "ngMask" must match templateOptions.ngMask
                        attribute: 'mask' // this the name of the attribute to be applied to the ng-model in the template
                    },
                    // applies the 'clean' attribute with the value of "true"
                    'true': {
                        value: 'clean'
                    }
                },
                // this is how you hook into formly's messages API
                // however angular-formly doesn't ship with ng-messages.
                // You have to display these messages yourself.
                validation: {
                    messages: {
                        mask: '"Nem megfelelő formátum"'
                    }
                }
            }
        });

        // alternatively, you could just use the directive in your template :-)
        formlyConfig.setType({
            name: 'lessMagicalMaskedInput',
            template: '<input ng-model="model[options.key]" mask="{{to.mask}}" clean="true" class="form-control" />',
            wrapper: ['bootstrapLabel', 'bootstrapHasError'],
            defaultOptions: {
                validation: {
                    messages: {
                        mask: '"Nem megfelelő formátum"'
                    }
                }
            }
        });

    }

    function setDatePicker(formlyConfig) {
        var attributes = [
            'date-disabled',
            'custom-class',
            'show-weeks',
            'starting-day',
            'init-date',
            'min-mode',
            'max-mode',
            'format-day',
            'format-month',
            'format-year',
            'format-day-header',
            'format-day-title',
            'format-month-title',
            'year-range',
            'shortcut-propagation',
            'datepicker-popup',
            'show-button-bar',
            'current-text',
            'clear-text',
            'close-text',
            'close-on-date-selection',
            'datepicker-append-to-body'
        ];

        var bindings = [
            'datepicker-mode',
            'min-date',
            'max-date'
        ];

        var ngModelAttrs = {};

        angular.forEach(attributes, function (attr) {
            ngModelAttrs[camelize(attr)] = {attribute: attr};
        });

        angular.forEach(bindings, function (binding) {
            ngModelAttrs[camelize(binding)] = {bound: binding};
        });

        console.log(ngModelAttrs);

        formlyConfig.setType({
            name: 'datepicker',
            templateUrl: 'datepicker.html',
            wrapper: ['bootstrapLabel', 'bootstrapHasError'],
            defaultOptions: {
                ngModelAttrs: ngModelAttrs,
                templateOptions: {
                    datepickerOptions: {
                        format: 'yyyy-MM-dd',
                        initDate: new Date()
                    }
                }
            },
            controller: ['$scope', function ($scope) {
                $scope.datepicker = {};

                $scope.datepicker.opened = false;

                $scope.datepicker.open = function ($event) {
                    $scope.datepicker.opened = true;
                };
            }]
        });
    }

    function setDateTimePicker(formlyConfig) {
        var attributes = [
            'data-ng-model',
            'data-datetimepicker-config',
        ];


        var ngModelAttrs = {};

        angular.forEach(attributes, function (attr) {
            ngModelAttrs[camelize(attr)] = {attribute: attr};
        });



        formlyConfig.setType({
            name: 'datetimepicker',
            templateUrl: 'datetimepicker.html',
            wrapper: ['bootstrapLabel', 'bootstrapHasError'],
            defaultOptions: {
                ngModelAttrs: ngModelAttrs,
                templateOptions: {
                }
            },
            controller: ['$scope', function ($scope) {
                $scope.change=function(item) {
                };
            }]
        });
    }

})();