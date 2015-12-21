/**
 * Created by Andrii Mozharovskyi on 29.10.2015.
 */
angular.module('eshop').factory('Utils', ['notify', function(notify){
    var self = this;

    self.notify = notify;
    self.notifySuccess = function(message) {
        self.notify(message);
    };
    self.notifyError = function(message) {
        self.notify(message, self.NotificationType.ERROR);
    };
    self.notifyWarn = function(message) {
        self.notify(message, self.NotificationType.WARN);
    };

    self.notify = function(message, type, _duration) {
        notify({
            message: message,
            position: "right",
            classes: type ? type : self.NotificationType.SUCCESS,
            duration: _duration || _duration === 0 ? _duration : ""
        });
    };

    self.NotificationType = {
        SUCCESS: "alert-success",
        WARN: "alert-warning",
        ERROR: "alert-danger"
    };

    return self;
}]);