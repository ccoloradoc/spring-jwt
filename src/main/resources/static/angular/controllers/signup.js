/**
 * Created by colorado on 29/03/17.
 */
angular.module('app')
    .controller('SignupController', ['$scope', 'Auth', '$location', function($scope, Auth, $location) {
        $scope.user = {
            username: "", password:""
        };

        $scope.submit = function() {
            Auth.signup($scope.user, function(result) {
                if(result){
                    $location.path('/login').search({signup: true});;
                } else {
                    $scope.error = {
                        message: 'Username has been used.',
                        type: 'deep-orange darken-1'
                    }
                }
            });
        }
    }]);