'use strict';

angular.module('myApp.alunos', ['ngRoute'])

    .controller('AlunosCtrl', ['$scope', '$http', 'acessos', 'Messages', 'Validation', 'Notifica', function ($scope, $http, acessos, Messages, Validation, Notifica) {
        $scope.Aluno = function () {
            return {
                id: null,
                nome: "",
                telefone: ""
            }
        };

        $scope.aluno = $scope.Aluno();

        // process the form
        $scope.limparForm = function () {
            $scope.aluno = $scope.Aluno();
            Validation.limpar('#aluno-form', false);
        };

        // process the form
        $scope.save = function () {

            $http({
                method: $scope.aluno.id ? 'PUT': 'POST',
                url: '/sat2-app/api/aluno',
                data: $.param($scope.aluno),  // pass in data as strings
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
            })
                .success(function (data) {
                    console.log(data);
                    $('#dataTables-alunos').DataTable().ajax.reload(null, false);
                    $scope.limparForm();
                    Notifica.exibaSucesso(null, Messages.salvoComSucesso("Aluno"));
                })
                .error(function (data) {

                    if (data != null) {
                        Validation.exibir('#aluno-form', data.validations, null, false);
                    } else {
                        Notifica.exibaErro(null, Messages.erroEfetuarOp);
                    }

                });

        };

        $scope.edit = function (id) {

            if ($scope.aluno.id != id) {

                $scope.limparForm();

                $http({
                    method: 'GET',
                    url: '/sat2-app/api/aluno/' + id
                }).success(function (aluno) {
                    $scope.aluno = aluno;
                }).error(function (data) {
                    Notifica.exibaErro(null, Messages.erroEfetuarOp);
                });

            }
        };

        var deleteSucesso = function () {
            $("#confirm-delete").modal('hide');
            $('#dataTables-alunos').DataTable().ajax.reload(null, false);
            $scope.aluno = $scope.Aluno();
            Notifica.exibaSucesso(null, Messages.registroRemovido);
        }

        $scope.delete = function () {


            if ($scope.aluno != null) {


                $http({
                    method: 'DELETE',
                    url: '/sat2-app/api/aluno/' + ($scope.aluno.id+1)
                }).success(function (response) {
                    deleteSucesso();
                }).error(function (response) {
                    if (response.status === 302) {
                        deleteSucesso();
                    } else {
                        Notifica.exibaErro(null, Messages.erroEfetuarOp);
                    }

                });


            }


        };

    }]);


