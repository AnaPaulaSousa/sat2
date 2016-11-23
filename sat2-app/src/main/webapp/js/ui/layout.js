/**
 * Created by diogo-vc on 01/11/2016.
 */

'use strict';

angular.module('myApp.layout', ['ngRoute'])
	.controller('LayoutCtrl', ['$scope', 'acessos', function ($scope, acessos) {
		$scope.acessos = acessos;
		Prism.highlightAll();

		$('#myTab a').click(function (e) {
			e.preventDefault();
		});
}]);