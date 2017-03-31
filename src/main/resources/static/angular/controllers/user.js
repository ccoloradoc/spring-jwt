/**
 * Created by colorado on 30/03/17.
 */
angular.module('app')
    .controller('UserController', ['$scope', '$routeParams', 'Resource', function($scope, $routeParams, Resource) {
        $scope.id = $routeParams.id || 1; //Replace with user id

        Resource.getTimezones($scope.id, function (timezoneList) {
           $scope.timezoneList = timezoneList;
           console.log(timezoneList);
        });

    }]);