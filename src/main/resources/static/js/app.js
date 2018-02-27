var app = angular.module('app', ['ngRoute','ngResource','ui.grid','ui.grid.pagination']);

app.config(function($routeProvider){
    $routeProvider
        .when('/phonenumber',{
            templateUrl: '/views/phonenumber.html',
            controller: 'phoneNumCombinationController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});