angular.module('myApp').factory('apphttpInterceptor', function ($q, $rootScope, $timeout) {
    console.log("Config  apphttpInterceptor")
    return {


        request: function(config) {
            console.log(config.url);
            return config;
        },


    };
});
