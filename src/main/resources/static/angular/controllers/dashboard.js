/**
 * Created by colorado on 29/03/17.
 */
angular.module('app')
    .controller('DashboardController', ['$scope', '$location', 'Auth', function($scope, $location, Auth) {
        $scope.isAuthenticated = function() {
            return Auth.isAuthenticated();
        };

        $scope.isNotAuthenticated = function() {
            return !Auth.isAuthenticated();
        };

        $scope.hasRole = function(role) {
            return Auth.isAuthenticated() && Auth.hasRole(role);
        };

        $scope.hasAnyRole = function() {
            return Auth.hasAnyRole(arguments);
        }

        $scope.logout = function() {
            Auth.logout();
            $location.path('/login');
        };
    }]);