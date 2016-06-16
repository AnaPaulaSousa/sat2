'use strict';

angular.module('myApp.alunosFormPadrao', ['ngRoute'])
    .controller('AlunosFormPadraoCtrl', ['$scope', '$http', 'acessos', 'Messages', 'Validation', 'Notifica', '$routeParams', function ($scope, $http, acessos, Messages, Validation, Notifica, $routeParams) {

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

        //metodo opcional
        $scope.erroAoSalvar = function(rejection) {
            //faz alguma coisa
            console.log("Erro ao Salvar:");
            console.log(rejection);
        }

        //metodo opcional
        $scope.sucessoAoSalvar = function(data) {
            //faz alguma coisa
            console.log("Sucesso ao Salvar:");
            console.log(data);
        }



        $scope.edit = function (id) {

            if (id && $scope.aluno.id != id) {

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


        $scope.delete = function () {
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


        $scope.edit($routeParams.id);


    }]);



