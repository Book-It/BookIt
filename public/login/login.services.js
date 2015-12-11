(function() {
  'use strict';
  angular
    .module('login')
    .factory('LoginService', function($http, moment, _) {

      var login = function(user) {
        var url = '/login/';
        return $http.post(url, user);
      };

      var newUser = function(user) {
        var url = '/create-account';
          if (user.password === user.password2) {
            var newUser = {
              username: user.username,
              password: user.password,
              firstName: user.firstName,
              lastName: user.lastName,
              city: user.city,
              state: user.state,
              email: user.email,
              phoneNum: user.phoneNum
            };
            return $http.post(url, newUser);
         } else {
           console.log("passwords do not match");
         }
        };
          
      return {
        login: login,
        newUser: newUser
      };

    });


}());
