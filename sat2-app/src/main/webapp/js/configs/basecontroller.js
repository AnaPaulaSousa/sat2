angular.module('myApp').controller('BaseController', ['RolesService', function(RolesService){
    this.isAllowed = function(role){
        return RolesService.contains(role);
    };
}]);

