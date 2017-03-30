/**
 * Created by colorado on 29/03/17.
 */
angular.module('app')
    .controller('LoginController', ['$scope', 'Auth', '$location', function($scope, Auth, $location) {
        $scope.user = {
            username: "", password:""
        };

        $scope.submit = function() {
            console.log($scope.user);
            Auth.login($scope.user, function(result) {
                if(result){
                    $location.path('/');
                } else {
                    $scope.error = {
                        message: 'Invalid username and password.',
                        type: 'deep-orange darken-1'
                    }
                }
            });
        }

        if($location.search().signup != undefined) {
            $scope.error = {
                message: 'You have been registered successfully. Please login.',
                type: 'green darken-1'
            }
        }
    }]);