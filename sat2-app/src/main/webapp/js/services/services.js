angular.module('myApp').service("RolesService", function($http, API_BASE_URL,CONTEXT_BASE_URL){

    var roles = [];

    this.contains = function(role){
        return roles.indexOf(role.toString()) != -1;
    }

    this.containsAny = function(roleList){

        index = -1;

        for(r in roleList){
            index = roles.indexOf(roleList[r]);
            if(index > 0) break;
        }

        return index > 0;

    }

    this.containsAny = function(roleList){

        index = -1;

        for(r in roleList){
            index = roles.indexOf(roleList[r]);
            if(index > 0) break;
        }

        return index > 0;

    }


    this.fetch = function(){
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
