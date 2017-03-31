/**
 * Created by colorado on 30/03/17.
 */
angular.module('app')
    .controller('UserController', ['$scope', '$routeParams', '$location', 'Resource',
        function($scope, $routeParams, $location, Resource) {
        $scope.id = $routeParams.id || 1; //Replace with user id

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
            $location.path('/user/' + $scope.id + '/timezone/' + timezone.id + '/edit');
        };

        $scope.add = function() {
            $location.path('/user/' + $scope.id + '/new');
        };

        $scope.loadTimezones();

    }]);