var tallerApp = angular.module('tallerApp', ['ngResource']);
tallerApp.config(function($routeProvider, $locationProvider) {
    $routeProvider.when('/', {
        templateUrl: 'login.html',
        controller: LoginController
    });

    $routeProvider.when('/profile/:userId', {
        templateUrl: 'profile.html',
        controller: ProfileController
    });
});

function LoginController($scope, $resource) {
    $scope.login = $resource('http://localhost:port/servicio/:action',
        {port:':9000', action: 'login', callback:'JSON_CALLBACK'});
    
    $scope.loguear = function (){
        $scope.loginResult = $scope.login.get({nickName:$scope.nick, password:$scope.pass});
    }
};


function ProfileController($scope, $routeParams, $resource) {
    $scope.usuario = $resource('http://localhost:port/servicio/:action',
        {port:':9000', action: 'usuario', callback:'JSON_CALLBACK'});
    console.log($routeParams.userId);
    $scope.usuarioResult = $scope.usuario.get({id:$routeParams.userId});
};