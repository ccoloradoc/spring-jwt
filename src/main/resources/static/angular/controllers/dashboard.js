/**
 * Created by colorado on 29/03/17.
 */
angular.module('app')
    .controller('DashboardController', ['$scope', '$rootScope', '$location', 'Auth', function($scope, $rootScope, $location, Auth) {
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

        $scope.closeMsg = function() {
            $rootScope.errorMessage = {
                enabled: false
            };
        };
    }]);