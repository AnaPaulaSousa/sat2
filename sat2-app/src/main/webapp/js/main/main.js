'use strict';

angular.module('myApp.main',[])
    .controller('MainCtrl', ['$scope', '$http', 'API_BASE_URL', 'Messages',  'Notifica', function($scope, $http, API_BASE_URL, Messages, Notifica) {

        this.logout = function() {

            $http({
                method  : 'GET',
                url     : API_BASE_URL+'/logout'
            }).success(function(apps) {

            }).error(function(data) {


            });
        };

    }]);

