/**
 * Created by Andrii on 16.12.2015.
 */
angular.module('eshop').controller("UserController", ["$state", "$rootScope", "$location", "Utils", "UserService", function($state, $rootScope, $location, Utils, UserService) {
    var self = this;
    self.info = UserService.getCurrent();

    self.logout = function() {
        UserService.empty();
        $state.go("login");
    };

    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){
        if(!UserService.getCurrent().id && toState.name !== "login") {
            event.preventDefault();
            $state.go("login");
        }
    });
}]);

angular.module('eshop').factory("UserService", ["RequestService", "Utils", function(RequestService, Utils) {
    var self = this;

    var current = {};

    self.setCurrent = function(user) {
        angular.copy(user, current);
    };

    self.getCurrent = function() {
        return current;
    };

    self.empty = function() {
        angular.copy({}, current);
    };

    return self;
}]);

angular.module('eshop').controller("loginController", ["$state", "Utils", "RequestService", "UserService", function($state, Utils, RequestService, UserService) {
    var self = this;

    self.credentials = {
        username: "",
        password: ""
    };

    self.login = function() {
        console.log(self.credentials);
        RequestService.get("/users/" + self.credentials.username).then(function success(response) {
            log.d(response);
            var userInfo = response.data;
            if(!userInfo.id) {
                loginError("Invalid data received!");
            }
            UserService.setCurrent(userInfo);
            if(userInfo.accountType === "ADMIN") {
                $state.go("admin");
            } else {
                $state.go("welcome");
            }
        }, function error(error) {
            loginError(error);
        });
    };

    function loginError(error) {
        log.e(error);
        Utils.notifyError("Authentication failed!");
    }

    self.register = function() {
        Utils.notifyWarn("Feature is under development!");
    }
}]);