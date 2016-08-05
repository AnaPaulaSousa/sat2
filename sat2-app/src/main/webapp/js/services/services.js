angular.module('myApp').service("RolesService", function($http, API_BASE_URL,CONTEXT_BASE_URL){

    var roles = [];

    this.contains = function(role){
        return roles.indexOf(role) > 0
    }

    this.fetch = function(){
        console.log("fectching");
        if(roles.length == 0){
            $http({
                method: "GET",
                url: CONTEXT_BASE_URL+'/userroles'
            }).success(function(r){
                roles = r;
            }).error(function(msg){
                console.log(msg);
            });
        }
    }

});
