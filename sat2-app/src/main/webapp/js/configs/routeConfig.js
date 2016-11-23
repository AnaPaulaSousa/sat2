angular.module("myApp").config(function ($routeProvider) {

	$routeProvider
		.when('/aluno-com-botao', {
			templateUrl: 'alunos/alunos.html',
			controller: 'AlunosCtrl',

			resolve: {
				acessos: function () {
					return "CIAE";
				}
			}

		})
		.when('/aluno-lista-pag-padrao', {
			templateUrl: 'alunos/alunos-lista.html',
			controller: 'AlunosListaPaginadaCtrl',

			resolve: {
				acessos: function () {
					return "CIAE";
				}
			}
		})
		.when('/aluno-form-padrao/:id?', {
			templateUrl: 'alunos/alunos-form.html',
			controller: 'AlunosFormPadraoCtrl',
			resolve: {
				acessos: function () {
					return "CIAE";
				}
			}
		})
		.when('/ui/layout', {
			templateUrl: 'ui/layout.html',
			controller: 'LayoutCtrl',
			resolve: { acessos: function () { return "CIAE"; } }
		})

		.otherwise({
			redirectTo: '/aluno-com-botao'
		});

});