'use strict';

// Declare app level module which depends on views, and components
var myApp = angular.module('myApp', [
    'ngRoute',
    'myApp.alunos',
    'myApp.alunosFormPadrao',
    'myApp.alunosListaPaginada',
    'myApp.version',
    'myApp.messages',
    'myApp.validations',
    'myApp.notifications',
    'goDataTable'
]).config(['$routeProvider', function () {

    $.notify.addStyle("segplan", {
        html: "<div> <span data-notify-text>  </span><div data-notify-html='mensagemHtml'> </div> </div>",
        classes: {
            base: {
                "background-repeat": "no-repeat",
                "background-position": "3px 7px",
                "width": "600px",
                "text-align": "center",
                "padding": "10px",
                "animation-name": "bounceInLeft",
                "webkit-animation-name": "bounceInLeft",
                "webkit-animation-duration": "1s",
                "animation-duration": "1s",
                "-webkit-animation-fill-mode": "both",
                "animation-fill-mode": "both",
                "border": "1px solid transparent",
                "border-radius": "3px",
            },
            error: {
                "color": "#fff",
                "background-color": "#EDA0A0",
                "border-color": "#F2633B"
            },
            success: {
                "color": "#fff",
                "background-color": "#9BDBB7",
                "border-color": "#D6E9C6"
            },
            info: {
                "color": "#3A87AD",
                "background-color": "#D9EDF7",
                "border-color": "#e5be56"
            },
            warn: {
                "color": "#666",
                "background-color": "#ffead0",
                "border-color": "#FBEED5"
            }
        }
    });


    $.notify.addStyle("segplan-modal", {
        html: "<div> <span data-notify-text></span> <div data-notify-html='mensagemHtml'> </div></div>",
        classes: {
            base: {
                "background-repeat": "no-repeat",
                "background-position": "3px 7px",
                "width": "400px",
                "text-align": "center",
                "padding": "10px",
                "animation-name": "bounceInLeft",
                "webkit-animation-name": "bounceInLeft",
                "webkit-animation-duration": "1s",
                "animation-duration": "1s",
                "-webkit-animation-fill-mode": "both",
                "animation-fill-mode": "both",
                "border": "1px solid transparent",
                "border-radius": "3px",
            },
            error: {
                "color": "#fff",
                "background-color": "#EDA0A0",
                "border-color": "#F2633B"
            },
            success: {
                "color": "#fff",
                "background-color": "#9BDBB7",
                "border-color": "#D6E9C6"
            },
            info: {
                "color": "#3A87AD",
                "background-color": "#D9EDF7",
                "border-color": "#e5be56"
            },
            warn: {
                "color": "#666",
                "background-color": "#ffead0",
                "border-color": "#FBEED5"
            }
        }
    });

}]);

myApp.value('API_BASE_URL', '/sat2-app/api');
