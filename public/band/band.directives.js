(function() {
  'use strict';
  angular
    .module('band')
      .directive('addBandForm', function() {
        return {
          restrict: 'E',
          templateUrl: 'band/views/addForm.html',
          link: function(scope,el,attr) {

          }
        };
      })
      .directive('upcomingShow', function() {
        return {
          restrict: 'E',
          templateUrl: 'band/views/upComingShows.html',
          controller: function($scope, $state, $stateParams, BandService) {

            $scope.showSettings = function () {
              if ($scope.settings === false) {
                $scope.settings = true;
                $scope.remove = true;
              } else {
                $scope.settings = false;
                $scope.remove = false;
              }
            };

            $scope.confirmShow = function (show) {
              console.log("before: ",show);
              BandService.confirmShow(show).success(function(data) {
                console.log(data);
              });

            };

            $scope.dangerZone = function() {
              $scope.danger = true;
              $scope.remove = false;
            };

            $scope.uncancel = function () {
              $scope.danger = false;
              $scope.remove = true;
            };

            $scope.cancelShow = function(show) {
              BandService.deleteShow(show);

              // put in link if possible
              var deletedShow = document.getElementById(show.id);
              deletedShow.remove();
            };

          },
          link: function(scope,el,attr) {

          }
        };
      });
}());
