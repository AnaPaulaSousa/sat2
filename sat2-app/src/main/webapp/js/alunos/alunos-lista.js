'use strict';

angular.module('myApp.alunosListaPaginada', ['ngRoute'])
    .controller('AlunosListaPaginadaCtrl', ['$scope', 'acessos', function ($scope, acessos) {
        $scope.acessos = acessos;
    }]);