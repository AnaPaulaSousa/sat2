angular.module('myApp')
    .config(function ($httpProvider) {
        $httpProvider.interceptors.push('apphttpInterceptor');
    })