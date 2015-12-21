/**
 * Created by Andrii on 16.12.2015.
 */
angular.module('eshop').controller("adminController", ["Utils", "UserService", "ProductService", "RequestService", function(Utils, UserService, ProductService, RequestService) {
    var self = this;

    self.product = {};
    self.products = ProductService.get();
    self.orders = [];

    self.fetchProducts = function() {
        ProductService.fetch();
    };

    self.fetchPendingOrders = function() {
        RequestService.get("/orders").then(function(response){
            var orders = response.data;
            var preparedOrders = [];
            for(var i = 0; i < orders.length; i++) {
                orders[i].ordered = new Date(orders[i].ordered);
                if(orders[i].status === "PENDING") {
                    preparedOrders.push(orders[i]);
                }
            }
            self.orders.refill(preparedOrders);
        }, function(error){
            log.e(error);
            Utils.notifyError("Failed to fetch orders!");
        });
    };

    self.addProduct = function() {
        RequestService.post("/products", self.product).then(function(){
            Utils.notifySuccess("Successfully saved!");
        }, function(error){
            log.e(error);
            Utils.notifyError("Failed to save product!");
        });
    };

    self.removeProduct = function(id) {
        RequestService.post("/products/"+id+"/remove").then(function(){
            Utils.notifySuccess("Successfully removed!");
            self.fetchProducts();
        }, function(error){
            log.e(error);
            Utils.notifyError("Failed to remove the product!");
        });
    };

    self.approve = function(order) {
        setOrderStatus(order.id, "PROCESSED");
    };

    self.decline = function(order) {
        setOrderStatus(order.id, "CLOSED");
    };

    function setOrderStatus(id, status) {
        RequestService.post("/orders/"+id+"/status/"+status).then(function(){
            Utils.notifySuccess("Successfully changed!");
            self.fetchPendingOrders();
        }, function(error){
            log.e(error);
            Utils.notifyError("Failed to change the order`s status!");
        });
    }

}]);