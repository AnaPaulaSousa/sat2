'use strict';

angular.module('myApp.alunos', ['ngRoute'])

    .controller('AlunosCtrl', ['$scope', '$http', 'acessos', 'Messages', 'Validation', 'Notifica', '$controller', function ($scope, $http, acessos, Messages, Validation, Notifica, $controller) {

        angular.extend(this, $controller('BaseController', {$scope: $scope}));

        $scope.Aluno = function () {
            return {
                id: null,
                nome: "",
                telefone: "",
                nascimento: null,
                sexo: null,
                situacao: null
            }
        };

        $scope.aluno = $scope.Aluno();

        // process the form
        $scope.limparForm = function () {
            $scope.aluno = $scope.Aluno();
            Validation.limpar('aluno-form', false);
        };

        // process the form
        $scope.save = function () {

            $http({
                method: 'POST',
                url: '/sat2-app/api/aluno',
                data: $.param($scope.aluno),  // pass in data as strings
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
            })
                .success(function (data) {
                    $('#dataTables-alunos').DataTable().ajax.reload(null, false);
                    $scope.limparForm();
                    Notifica.exibaSucesso(null, Messages.salvoComSucesso("Aluno"));
                })
                .error(function (data) {

                    if (data != null) {
                        Validation.exibir('aluno-form', data.validations, null, false);
                    } else {
                        Notifica.exibaErro(null, Messages.erroEfetuarOp);
                    }

                });

        };

        $scope.edit = function (id) {

            if ($scope.aluno.id != id) {

                $scope.isUpdate = true;

                $scope.limparForm();

                $http({
                    method: 'GET',
                    url: '/sat2-app/api/aluno/' + id
                }).success(function (aluno) {
                    if (aluno.nascimento){
                        aluno.nascimento = new Date(aluno.nascimento);
                    }
                    $scope.aluno = aluno;
                }).error(function (data) {
                    Notifica.exibaErro(null, Messages.erroEfetuarOp);
                });

            }
        };


        $scope.delete = function () {

            this.isUpdate = true;

            if ($scope.aluno != null) {
                $http({
                    method: 'DELETE',
                    url: '/sat2-app/api/aluno/' + $scope.aluno.id
                }).success(function (response) {
                    $("#confirm-delete").modal('hide');
                    $('#dataTables-alunos').DataTable().ajax.reload(null, false);
                    $scope.aluno = $scope.Aluno();
                    Notifica.exibaSucesso(null, Messages.registroRemovido);
                }).error(function (response) {
                    Notifica.exibaErro(null, Messages.erroEfetuarOp);
                });
            }
        };

    }]);


