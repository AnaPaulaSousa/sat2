/**
 * Created by ederbd on 14/06/16.
 */
angular.module('goForm', []).directive("goForm", function () {
    return {
        restrict: 'A',
        scope: {goFormModel: '=', goFormOnErrorFn: '=', goFormOnSuccessFn: '='},
        controller: function ($scope, $element, $attrs, $location, $window, $http, Notifica, Validation, Messages) {

            this.submit = function () {
                $http({
                    method: 'POST',
                    url: $attrs.goFormAction,
                    data: $.param($scope.goFormModel),
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                })
                    .success(function (data) {

                        if ($scope.goFormOnSuccessFn) {
                            $scope.goFormOnSuccessFn(data);
                        } else {
                            if ($attrs.goFormRedirect) {
                                if (/(#)/.test($attrs.goFormRedirect)) {
                                    var url = $attrs.goFormRedirect.replace("#", "");
                                    if (data && data.id){
                                        $location.path(url).search({id: data.id});
                                    }else{
                                        $location.path(url);
                                    }
                                    $window.location.href = $location.absUrl();
                                } else {
                                    $window.location.href = $attrs.goFormRedirect;
                                }
                            }
                            if (Notifica && Notifica.exibaSucesso && Messages && Messages.salvoComSucesso) {
                                Notifica.exibaSucesso(null, Messages.salvoComSucesso(""));
                            } else {
                                console.log("Sucesso ao executar a operação!");
                            }
                        }


                    })
                    .error(function (data) {

                        if ($scope.goFormOnErrorFn) {
                            $scope.goFormOnErrorFn(data);
                        } else {
                            if ($attrs.goFormOnErrorMsg) {
                                if (Notifica && Notifica.exibaErro) {
                                    Notifica.exibaErro(null, $attrs.goFormOnErrorMsg);
                                } else {
                                    console.log($attrs.goFormOnErrorMsg);
                                }
                            } else if (data != null && Validation && Validation.exibir) {
                                Validation.exibir('#' + $attrs.id, data.validations, null, false);
                            } else if (Notifica && Notifica.exibaErro && Messages) {
                                Notifica.exibaErro(null, Messages.erroEfetuarOp);
                            } else {
                                console.log("Erro ao executar a operação!");
                            }
                        }


                    });

            };

        }

    };
});
angular.module('goForm').directive("goSubmit", function () {

    return {
        require: "^goForm",
        restrict: 'A',
        template: function (tElement, tAttrs) {
            var clickAttr = tAttrs.ngClick ? '' : 'ng-click="goFormSubmitFn()"';
            return '<div ' + clickAttr + ' ng-transclude></div>';
        },
        transclude: true,
        link: function (scope, element, attr, ctrl) {
            if (!attr.ngClick || attr.ngClick.indexOf("goFormSubmitFn") > -1) {
                scope.goFormSubmitFn = function () {
                    ctrl.submit();
                };
            }
        }
    };

});
