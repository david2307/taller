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

function LoginController($scope, $resource, $location) {
    $scope.login = $resource('../../servicio/:action',
        {port:':9000', action: 'login', callback:'JSON_CALLBACK'});
    
    $scope.loguear = function (){
        $scope.loginResult = $scope.login.get({nickName:$scope.nick, password:$scope.pass}, function (data) {
            if (data.result == "true") {
                console.log("Estoy dentro del true" + data.result);
                var path = "/profile/"+data.id
                $location.path(path);
            }else {
                console.log("Estoy en false");
            }
        });
    }
};


function ProfileController($scope, $routeParams, $resource) {
    $scope.usuario = $resource('../../servicio/:action',
        {port:':9000', action: 'usuario', callback:'JSON_CALLBACK'});
    console.log($routeParams.userId);
    $scope.usuarioResult = $scope.usuario.get({id:$routeParams.userId});
};