var app = angular.module('scrumRetro', ['ngRoute']);

//This configures the routes and associates each route with a view and a controller
app.config(function ($routeProvider) {
    $routeProvider
        .when('/team',
            {
                controller: 'teamController',
                templateUrl: '/app/partials/teams.html'
            })
        .when('/dashboard',
            {
                controller: 'dashboardController',
                templateUrl: '/app/partials/dashboard.html'
        })
        .otherwise({ redirectTo: '/dashboard' });
});




