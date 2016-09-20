var messages = angular.module('myApp.messages', []);

messages.factory('Messages', [
    function(){

        return {
            salvoComSucesso: function (registro) { return '${registro} salvo com sucesso'},
            registroRemovido: "O registro foi removido",
            erroEfetuarOp: "Não foi possível efetuar a operação",
        };
    }
]);
