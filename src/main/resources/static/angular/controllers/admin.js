/**
 * Created by colorado on 31/03/17.
 */
angular.module('app')
    .controller('AdminController', ['$scope', '$timeout', '$location',  'Admin', function($scope, $timeout, $location, Admin) {
        Admin.User.query(function(users) {
           $scope.users = users;
        });

        Admin.Role.query(function(roles) {
            $scope.roles = roles;

            $timeout(function(){
                $('select').material_select();
            },0,false);
        });

        $scope.submit = function () {
            Admin.User.save($scope.user, function() {
                $location.path('/admin');
            });
        }

    }]);