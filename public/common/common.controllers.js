(function() {
  'use strict';
  angular
    .module('bookit')
    .controller('MainController', function(MainService, $state, $scope) {
      var vm = this;
      console.log("I AM MAIN CTRL");
      var checkUser = function() {
        MainService.check().success(function(user) {
          console.log("Logged in user: ", user);
        }).error(function() {
          $state.go('login');
        });
      };

        // checkUser();

      vm.logout = function() {
        MainService.check().success(function(user) {
          console.log("target: ", user);
          MainService.endSession(user).success(function() {
            $state.go('login');
          });
        });
      };

    });


}());
