(function() {
  'use strict';
  angular
  .module('band', [
    'ui.router',
  ])
  .config(function($stateProvider) {
    $stateProvider
    .state('band', {
      url: '/band/:bandId',
      templateUrl: 'band/views/bandDetails.html',
      controller: 'DetailController as DController'
    })

    .state('createband', {
      url: '/band/create',
      templateUrl: 'band/views/createBand.html',
      controller: 'BandController as BController'
    });
  });




}());
