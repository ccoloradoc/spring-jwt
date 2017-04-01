/**
 * Created by colorado on 31/03/17.
 */
angular.module('app')
    .controller('AdminController', ['$scope', '$timeout', '$route',  'Admin', function($scope, $timeout, $route, Admin) {
        Admin.User.query(function(users) {
           $scope.users = users;
        });

        Admin.Role.query(function(roles) {
            $scope.roles = roles;

            $timeout(function(){
                $('select').material_select();
            },0,false);
        });

        $scope.clean = function () {
            $scope.user = {};
        };

        $scope.delete = function (user) {
            Admin.User.remove(user.id, function() {
                $route.reload();
            });
        };

        $scope.edit = function (user) {
            $scope.user = user;
            $timeout(function(){
                $('select').material_select();
            },0,false);
        };

        $scope.save = function () {
            console.log($scope.user);
            if($scope.user.id != undefined) {
                Admin.User.update($scope.user, function() {
                    $route.reload();
                });
            } else {
                Admin.User.save($scope.user, function() {
                    $route.reload();
                });
            }
        }

    }]);