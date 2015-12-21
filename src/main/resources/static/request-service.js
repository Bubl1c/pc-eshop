/**
 * Created by Andrii on 31.10.2015.
 */
angular.module('eshop').factory('RequestService', ['$http', '$location', function($http, $location){
    var self = this;

    self.baseURL = 'http://' + $location.host() + ':' + $location.port();

    self.get = function(path, headers) {
        return $http({
            method: 'GET',
            url: self.baseURL + path,
            headers: headers ? headers : {},
            contentType: "application/json"
        });
    };

    self.post = function(path, data, headers) {
        return $http({
            method: 'POST',
            url: self.baseURL + path,
            data: data ? data : null,
            headers: headers ? headers : {},
            contentType: "application/json"
        });
    };

    return self;
}]);