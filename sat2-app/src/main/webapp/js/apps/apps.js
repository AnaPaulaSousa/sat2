'use strict';

angular.module('myApp.apps', ['ngRoute'])

	.config(['$routeProvider', function($routeProvider) {
		$routeProvider.when('/apps', {
			templateUrl: 'apps/apps.html',
			controller: 'AppsCtrl'
		});
	}])

	.controller('AppsCtrl', ['$scope', '$http', '$timeout', 'API_BASE_URL', 'Messages',  'Notifica', function($scope, $http, $timeout, API_BASE_URL, Messages, Notifica) {

		$scope.apps = [];

		$scope.error = 1;
		$scope.counter = 10;

		var mytimeout = null;

		$scope.onTimeout = function(){
			$scope.counter--;

			if($scope.counter < 1){
				$scope.findAll();
			}else {
				mytimeout = $timeout($scope.onTimeout,1000);
			}
		}

		$scope.findAll = function() {

			$(".wrap").css("margin-left", "0");
			$(".navbar").css("display", "none");

			$("#retry").css("visibility", "hidden");
			$("#loading").css("visibility", "visible");


			$http({
				method  : 'GET',
				url     : API_BASE_URL+'/apps/home'
			}).success(function(apps) {
				$('#loading').remove();
				$('#retry').remove();

				if(apps.length == 0){
					$("#semAcesso").css("visibility", "visible");
				}else{
					$scope.apps = apps;
					$('#semAcesso').remove();

					$timeout(function() {
						Gallery.init();
					})

				}

			}).error(function(data) {
				$("#loading").css("visibility", "hidden");

				$scope.counter = 10 * $scope.error;
				$scope.error++

				mytimeout = $timeout($scope.onTimeout,1000);

				$("#retry").css("visibility", "visible");

			});
		};

	}]);

