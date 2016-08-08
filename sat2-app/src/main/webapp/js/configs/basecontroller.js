angular.module('myApp').controller('BaseController', ['RolesService', function(RolesService){

    this.isUpdate = false;

    this.isAllowed = function(role){
        return RolesService.contains(role);
    }

    this.isAllowedAny = function(role){
        return RolesService.containsAny(role);
    };

}]);

