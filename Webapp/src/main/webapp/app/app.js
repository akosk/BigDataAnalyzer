/**
 * Created by Ákos on 2015.06.22..
 */

var VERSION = '1';

(function () {
    "use strict";

    var app = angular.module("app", [
        'ngRoute',
        'ngAnimate',
        'ui.bootstrap',
        angularDragula(angular),
        'formly',
        'formlyBootstrap',
        'ngMask'])
        .run(formlyInit);


    function formlyInit(formlyConfig) {
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
                        mask: '"Invalid input"'
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
                        mask: '"Invalid input"'
                    }
                }
            }
        });

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

        angular.forEach(attributes, function(attr) {
            ngModelAttrs[camelize(attr)] = {attribute: attr};
        });

        angular.forEach(bindings, function(binding) {
            ngModelAttrs[camelize(binding)] = {bound: binding};
        });

        console.log(ngModelAttrs);

        formlyConfig.setType({
            name: 'datepicker',
            templateUrl:  'datepicker.html',
            wrapper: ['bootstrapLabel', 'bootstrapHasError'],
            defaultOptions: {
                ngModelAttrs: ngModelAttrs,
                templateOptions: {
                    datepickerOptions: {
                        format: 'yyyy.MM.dd',
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

        formlyConfig.setType({
            name: 'rangeInput',
            template:'halló'
        });

    };

    function camelize(string) {
        string = string.replace(/[\-_\s]+(.)?/g, function(match, chr) {
            return chr ? chr.toUpperCase() : '';
        });
        // Ensure 1st char is always lowercase
        return string.replace(/^([A-Z])/, function(match, chr) {
            return chr ? chr.toLowerCase() : '';
        });
    }

})();