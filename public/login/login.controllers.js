(function() {
  'use strict';
  angular
    .module('login')
    .controller('LoginController', function($scope, LoginService, $state) {

      var vm = this;

      vm.login = function(user) {

        LoginService.login(user).success(function(data) {
          $state.go('home');
        })
      };

      vm.createNewUser = function(user) {
        LoginService.newUser(user).success(function() {
          $state.go('home');
        });
      };

    });


}());
