angular.module('myApp.validations', [])
    .factory('Validation', [
        function(){
            var validarClienteSide = function (formulario) {

                this.limpar(formulario);
                return validador(formulario).validate();
            };
            var validador = function (formulario) {
                return $(formulario).parsley();
            };
            var limpar = function (formulario, parsley) {

                if(parsley){
                    validador(formulario).reset();
                }else{

                    $('#'+formulario+' :input').each(function(){
                        var nome = $(this).attr("name");
                        $(this).removeClass('parsley-error');
                        $('#'+nome+'-errors').empty();
                    });

                }

            };

            var exibir = function (formulario, inconsistencias, mensagemDetalhada, parsley) {

                this.limpar(formulario, parsley);

                if (inconsistencias == null || inconsistencias == undefined) {
                    mensagemNotificacaoGeral.exibaErro(mensagemDetalhada);
                } else {

                    for(iconsistencia in inconsistencias){

                        var msgs = inconsistencias[iconsistencia];

                        var encontrado = false;

                        if(parsley){

                            validador(formulario).fields.forEach(function (e, i) {
                                var nome = e.$element.attr("name");
                                if (nome != undefined && iconsistencia == nome) {
                                    encontrado = true;

                                    msgs.forEach(function(msg,ind){
                                        ParsleyUI.addError(e, nome, msg)
                                    });

                                }
                            });

                        }else{

                            $('#'+formulario+' :input').each(function(){

                                var self = this;

                                var nome = $(self).attr("name");

                                if (nome != undefined && iconsistencia == nome) {
                                    encontrado = true;

                                    msgs.forEach(function(msg,ind){
                                        $(self).addClass('parsley-error');
                                        $('#'+nome+'-errors').append("<li class='parsley-required'>"+msg+"</li>");
                                    });
                                }

                            });

                        }

                        if (!encontrado) {
                            //inconsistenciasSemVinculo[indice] = elemento;
                        }

                    }

                    //mensagemNotificacaoGeral.exibaErroDetalhado(inconsistenciasSemVinculo);
                }

            };
            return {
                exibir: exibir,
                limpar: limpar,
                validar: validarClienteSide,
            };
        }
    ]);
