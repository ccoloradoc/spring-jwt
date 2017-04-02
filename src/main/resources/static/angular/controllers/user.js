/**
 * Created by colorado on 30/03/17.
 */
angular.module('app')
    .controller('UserController', ['$scope', '$routeParams', '$location', 'Auth', 'Resource',
        function($scope, $routeParams, $location, Auth, Resource) {
        $scope.id = $routeParams.id || Auth.currentUser().id;

        $scope.loadTimezones = function() {
            Resource.getTimezones($scope.id, function (timezoneList) {
                $scope.timezoneList = timezoneList;
            });
        };

        $scope.delete = function(timezone) {
            Resource.deleteTimezone($scope.id, timezone, function() {
                $scope.loadTimezones();
            });
        };

        $scope.edit = function(timezone) {
            $location.path('/' + $scope.id + '/timezone/' + timezone.id + '/edit');
        };

        $scope.add = function() {
            $location.path('/' + $scope.id + '/new');
        };

        $scope.loadTimezones();

    }]);