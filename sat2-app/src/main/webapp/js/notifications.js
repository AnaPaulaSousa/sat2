/* Services */

var notifications = angular.module('myApp.notifications', []);

notifications.factory('Notifica', [
    function(){

        var exibaMensagem = function (tipoMensagem, escopo, mensagemPadrao, inconsistencias) {

            if (inconsistencias && inconsistencias.length > 0) {
                var erro = "";

                inconsistencias.forEach(function (e, i) {
                    erro += "<span> <strong> " + e.Propriedade + " </strong> - " + e.Mensagem + "</span> </br>";
                });

                var mensagemCompleta = "<strong>Erro! Verifique as regras: </strong> </div> </br>" + erro;

                if (escopo != undefined) {
                    $(escopo).notify({ mensagemHtml: mensagemCompleta }, { autoHideDelay: 7000, style: "segplan-modal", position: "top-center", className: tipoMensagem });
                } else {
                    $.notify({ mensagemHtml: mensagemCompleta }, { autoHideDelay: 7000, style: "segplan", className: tipoMensagem })
                }
            }
            else {

                if (escopo != undefined) {
                    $(escopo).notify({ mensagemHtml: mensagemPadrao } , { autoHideDelay: 7000, style: "segplan-modal", position: "top-center", className: tipoMensagem });
                } else {
                    $.notify({ mensagemHtml: mensagemPadrao }, { autoHideDelay: 7000, style: "segplan", className: tipoMensagem })
                }
            }
        };

        var exibaErroDetalhado = function (escopo, inconsistencias) {
            return exibaMensagem('error', escopo, "Existem inconsistências.", inconsistencias);
        };

        var exibaErro = function (escopo, mensagem) {
            return exibaMensagem('error', escopo, '<strong>Erro!</strong> ' + mensagem + '.');
        };

        var exibaNotificacao = function (escopo, mensagem) {
            return exibaMensagem('warning', escopo, mensagem);
        };

        var exibaSucesso = function (escopo, mensagem) {
            return exibaMensagem('success', escopo, mensagem);
        };

        return {
            exibaErroDetalhado: exibaErroDetalhado,
            exibaNotificacao: exibaNotificacao,
            exibaSucesso: exibaSucesso,
            exibaErro: exibaErro
        };
    }
]);


// notifications.factory('NotificaModal', [
//     function(){
//
//         var exibaErroDetalhado = function (inconsistencias) {
//             return mensagemNotificacao.exibaErroDetalhado($(".modal.in .modal-content"), inconsistencias);
//         }
//
//         var exibaNotificacao = function (mensagem) {
//             return mensagemNotificacao.exibaNotificacao($(".modal.in .modal-content"), mensagem);
//         }
//
//         var exibaSucesso = function (mensagem) {
//             return mensagemNotificacao.exibaSucesso($(".modal.in .modal-content"), mensagem);
//         }
//
//         return {
//             exibaErroDetalhado: exibaErroDetalhado,
//             exibaNotificacao: exibaNotificacao,
//             exibaSucesso: exibaSucesso
//         }
//
//     }
// ]);
//
//
// notifications.factory('NotificaGeral', [
//     function(){
//
//         var exibaErroDetalhado = function (inconsistencias) {
//             return mensagemNotificacao.exibaErroDetalhado(undefined, inconsistencias);
//         }
//
//         var exibaNotificacao = function (mensagem) {
//             return mensagemNotificacao.exibaNotificacao(undefined, mensagem);
//         }
//
//         var exibaSucesso = function (mensagem) {
//             return mensagemNotificacao.exibaSucesso(undefined, mensagem);
//         }
//
//         var exibaSucessoPadrao = function (detalhamento) {
//             if (detalhamento != undefined) {
//                 return this.exibaSucesso('<strong>Sucesso!</strong> ' + detalhamento);
//             } else {
//                 return this.exibaSucesso('<strong>Sucesso!</strong> Operação realizada.');
//             }
//         }
//
//         var exibaErro = function (mensagem) {
//             return mensagemNotificacao.exibaErro(undefined, mensagem);
//         }
//
//         return {
//             exibaErro: exibaErro,
//             exibaNotificacao: exibaNotificacao,
//             exibaSucesso: exibaSucesso,
//             exibaSucessoPadrao: exibaSucessoPadrao,
//             exibaErroDetalhado: exibaErroDetalhado
//         }
//     }
// ]);


