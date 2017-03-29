/**
 * Created by colorado on 29/03/17.
 */
angular.module('app')
    .config(['$httpProvider', function($httpProvider) {
        // $httpProvider.interceptors.push(function($q, $location,$rootScope) {
        // 	return {
        // 		response: function(response) {
        // 			return response;
        // 		},
        // 		responseError: function(response) {
        // 			console.log('Response: ' );
        // 			console.log(response);
        // 			if (response.status === 401)  {
        // 				$rootScope.message = 'You need to log in.';
        //                 $scope.error = {
        //                     message: 'Invalid username and password.',
        //                     type: 'deep-orange darken-1'
        //                 }
        // 				$location.url('/');
        // 			}
        //
        // 			return $q.reject(response);
        // 		}
        // 	};
        //});
    }])
    .run(['$rootScope', '$location', function ($rootScope, $location) {
        $rootScope.$on('$routeChangeError', function(event, next, current) {
            //console.log(current);
            if(current !== undefined)
                $location.url(current.$$route.originalPath);
            else
                $location.path('/login');
        });
    }])
    .constant('policies',{
        '/': {
            templateUrl: 'angular/templates/dashboard.html',
            controller: 'DashboardController',
            permissions: ['ADMIN', 'MANAGER', 'USER']
        },
        '/login': {
            templateUrl: 'angular/templates/login.html',
            controller: 'LoginController'
        }
    })
    .config(['$routeProvider', 'policies', function($routeProvider, policies) {
        //Our NOT THAT complex logic for authentification and authorization validation
        var authResolver = function(path) {
            return {
                routingMessage : function(Auth, $q) {
                    var deferred = $q.defer();

                    Auth.hasUserPermissionForView(path)
                        .then(function(msg) {
                            deferred.resolve();
                        }, function() {
                            deferred.reject();
                        });

                    return deferred.promise;
                }
            }
        };

        //Configuring Routes and Auth
        for(var path in policies) {
            //Build Route
            var route = {
                templateUrl: policies[path].templateUrl,
                controller: policies[path].controller
            };

            //Sync with server about user status
            route.resolve =  authResolver(path);

            //Register route
            $routeProvider.when(path, route);
        }

        $routeProvider.otherwise({redirectTo: '/'});
    }]);
