/**
 * Created by Andrii on 16.12.2015.
 */
angular.module('eshop', ['ui.bootstrap', 'ui.router', 'cgNotify', 'ngCookies']);

angular
    .module('eshop')
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

        $urlRouterProvider.otherwise('/login');

        $stateProvider
            .state('login', {
                url: '/login',
                templateUrl: "static/partials/login/login.html",
                controller: 'loginController',
                controllerAs: 'login'
            })
            .state('login.register', {
                url: '/register',
                templateUrl: "static/partials/login/register.html",
                controller: 'registrationController',
                controllerAs: 'register'
            })
            .state('welcome', {
                url: '/',
                templateUrl: "static/partials/welcome/welcome.html",
                controller: 'welcomePageController',
                controllerAs: 'welcome'
            })
            .state('order_submitted', {
                url: '/orders/submitted',
                templateUrl: "static/partials/order/submitted.html",
                controller: function($scope, $stateParams) {
                    $scope.submittedOrderId = $stateParams.orderId;
                },
                params: {
                    orderId: null
                }
            })
            .state('my_orders', {
                url: '/orders',
                templateUrl: "static/partials/order/my.html",
                controller: "MyOrdersController",
                controllerAs: 'MyOrders'
            })
            .state('admin', {
                url: '/admin',
                templateUrl: "static/partials/admin/admin.html",
                controller: "adminController",
                controllerAs: 'Admin'
            })
            .state('admin.products', {
                url: '/products',
                templateUrl: "static/partials/admin/products.html"/*,
                controller: "adminController",
                controllerAs: 'Admin'*/
            })
            .state('admin.add', {
                url: '/products/add',
                templateUrl: "static/partials/admin/add_products.html"/*,
                controller: "adminController",
                controllerAs: 'Admin'*/
            })
            .state('admin.orders', {
                url: '/orders',
                templateUrl: "static/partials/admin/orders.html"/*,
                controller: "adminController",
                controllerAs: 'Admin'*/
            });
            //.state('welcome.login.sign', {
            //    url: '/sign',
            //    templateUrl: 'static/components/login/login.html',
            //    controller: 'LoginController',
            //    controllerAs: 'LoginCtrl',
            //    params: {
            //        warning: null
            //    }
            //})
    });

angular.module('eshop').controller("welcomePageController", ["Utils", "ProductService", "CartService", function(Utils, ProductService, CartService) {
    var self = this;
    this.products = ProductService.get();
    ProductService.fetch();
    self.addToCart = function(product) {
        CartService.addProduct(product);
    }

}]);

angular.module('eshop').controller("cartController", ["$state", "Utils", "CartService", "RequestService", function($state, Utils, CartService, RequestService) {
    var self = this;

    self.products = CartService.getProducts();
    self.summary = CartService.getSummary();

    self.removeProduct = function(product) {
        CartService.removeProduct(product);
    };

    self.submitOrder = function(currentUser) {
        var order = prepareOrder(currentUser);
        RequestService.post("/orders", order).then(function(response) {
            var orderId = response.data;
            $state.go("order_submitted", {orderId: orderId});
            CartService.empty();
        }, function(error) {
            log.e(error);
            Utils.notifyError("Failed to save order!");
        });
    };

    function prepareOrder(currentUser) {
        var order = {};
        order.customerId = currentUser.username;
        order.payment = {
            paid: new Date().getTime(),
            totalSum: self.summary.total,
            details: "Payed successfully"
        };
        order.shipping = {
            price: 5,
            type: "COURIER",
            country: "Ukr",
            city: "K",
            street: "street",
            buildingNumber: 3,
            apartment: 402,
            zip: "03056"
        };
        order.rows = prepareRows();
        return order;
    }

    function prepareRows() {
        var rows = [];
        angular.forEach(self.products, function(product) {
            rows.push({
                productId: product.id,
                quantity: product.amount
            });
        });
        return rows;
    }
}]);

angular.module('eshop').factory("CartService", ["RequestService", "Utils", function(RequestService, Utils) {
    var self = this;

    var products = [];
    var summary = {};
    summary.total = 0;

    function indexOf(product) {
        for(var i = 0; i < products.length; i++) {
            if(products[i].id === product.id) {
                return i;
            }
        }
        return -1;
    }

    function recalculateSummary() {
        summary.total = 0;
        for(var i = 0; i < products.length; i++) {
            summary.total += products[i].price * products[i].amount;
        }
    }

    self.addProduct = function(product) {
        var productToAdd = angular.copy(product);
        var index = indexOf(productToAdd);
        if(index !== -1) {
            products[index].amount++;
        } else {
            productToAdd.amount = 1;
            products.push(productToAdd);
        }
        recalculateSummary();
    };

    self.removeProduct = function(product) {
        var index = indexOf(product);
        if (index > -1) {
            products.splice(index, 1);
        }
        recalculateSummary();
    };

    self.getProducts = function() {
        return products;
    };

    self.getSummary = function() {
        return summary;
    };

    self.empty = function() {
        products.length = 0;
        recalculateSummary();
    };

    return self;
}]);

angular.module('eshop').controller("MyOrdersController", ["$state", "Utils", "RequestService", "UserService", function($state, Utils, RequestService, UserService) {
    var self = this;

    self.list = [];

    RequestService.get("/orders/" + UserService.getCurrent().username).then(function(response){
        var orders = response.data;
        for(var i = 0; i < orders.length; i++) {
            orders[i].ordered = new Date(orders[i].ordered);
        }
        self.list.refill(orders);
    }, function(error){
        log.e(error);
        Utils.notifyError("Failed to fetch orders!");
    });
}]);

angular.module('eshop').factory("ProductService", ["RequestService", "Utils", function(RequestService, Utils) {
    var self = this;

    self.products = [];

    self.fetch = function() {
        RequestService.get("/products").then(function success(response) {
            log.d(response);
            self.products.refill(response.data);
        }, function error(error) {
            log.e(error);
            Utils.notifyError("Failed to fetch products!");
        });
    };

    self.get = function() {
        return self.products;
    };

    return self;
}]);

angular.module('eshop').directive("productLayout", function() {
    return {
        restrict: 'E',
        scope: {
            product: '=data'
        },
        templateUrl: '/static/partials/welcome/product.html'
    };
});

angular.module('eshop').directive("cartEntryLayout", function() {
    return {
        restrict: 'E',
        scope: {
            product: '=data'
        },
        templateUrl: '/static/partials/welcome/cart-entry.html'
    };
});

angular.module('eshop').directive("orderInListLayout", function() {
    return {
        restrict: 'E',
        scope: {
            order: '=data'
        },
        templateUrl: '/static/partials/order/order.html'
    };
});

//angular.module("cyberpol").run(function ($rootScope) {
//    $rootScope.$on('$stateChangeError', function (event, toState, toParams, fromState, fromParams, error) {
//        console.log('Resolve Error: ', error);
//    });
//})