/**
 * Created by vpetrovskyi on 27.10.2015.
 */
Array.prototype.refill = function(elements) {
    this.length = 0;
    var self = this;
    angular.forEach(elements, function(value) {
        self.push(value);
    });
};

var log = (function(){
    var debugging = true;

    function printAll(elements, error) {
        for(var i = 0; i < elements.length; i++) {
            if(error) {
                console.error(elements[i]);
            } else {
                console.log(elements[i]);
            }
        }
    }

    return {
        /**
         * Use for debug messages
         */
        d: function() {
            if(debugging) { printAll(arguments); }
        },
        /**
         * Use for information messages
         */
        i: function() {
            printAll(arguments);
        },
        /**
         * Use for error messages
         */
        e: function() {
            printAll(arguments, true);
        }
    };
}());