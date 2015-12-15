(function() {
  'use strict';
  angular
    .module('events', [
      'ui.router'
    ])
    .config(function($stateProvider) {
      $stateProvider
      .state('main.show', {
        url: '/show',
        views: {
          'chewy': {
            templateUrl: 'events/views/show.html',
            controller: 'EventsController as EController'
          }
        }
      })
      .state('main.tour', {
        url: '/tour',
        views: {
          'chewy': {
            templateUrl: 'events/views/tour.html',
            controller: 'EventsController as EController'
          }
        }
      });
    });



}());
