angular.module('myApp').factory('apphttpInterceptor', function ($q, $rootScope, $timeout) {
    // console.log("Config  apphttpInterceptor")
    return {


        request: function(config) {
            // console.log("request");
            //console.log(config.url);
            return config;
        },
        // optional method
        requestError: function(rejection) {
            // console.log("requestError");
            // console.log(rejection);

            return $q.reject(rejection);
        },
        // optional method
        response: function(response) {
            // console.log("response");
            // console.log(response);
            return response;
        },
        // optional method
        responseError : function(rejection) {
            // console.log("responseError");
            // console.log(rejection);

            return $q.reject(rejection);
        }


    };
});
