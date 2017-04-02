/**
 * Created by colorado on 29/03/17.
 */
angular.module('app')
    .factory('Auth', function($http, $q, $localStorage, policies){
        $http.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

        var isAuthenticated = function () {
            return $localStorage.currentUser != undefined;
        };

        var hasAnyRole = function(roles) {
            if($localStorage.currentUser != undefined) {
                for(var i in $localStorage.currentUser.authorities) {
                    var authority = $localStorage.currentUser.authorities[i];
                    for(var j in roles) {
                        var role = roles[j];
                        if(authority.authority == role) {
                            return true;
                        }
                    }
                }
            }
            return false;
        };

        var hasRole = function(targetRole) {
            if($localStorage.currentUser != undefined) {
                for(var i in $localStorage.currentUser.authorities) {
                    var role = $localStorage.currentUser.authorities[i];
                    if(role.authority == targetRole)
                        return true;
                };
            }
            return false;
        };


        return {
            isAuthenticated: isAuthenticated,
            hasAnyRole: hasAnyRole,
            hasRole: hasRole,
            currentUser: function() {
              return $localStorage.currentUser || {};
            },
            hasUserPermissionForView: function(view) {
                var deferred = $q.defer();
                var policy = policies[view];
                if(!policy.permissions || !policy.permissions.length){
                    deferred.resolve({type: 'success', text: 'View is public.'});
                } else {
                    if($localStorage.currentUser == undefined) {
                        deferred.reject({type: 'danger', text: 'User has NOT permissions.'});
                    } else {
                        var found = false;
                        angular.forEach(policy.permissions, function(permission){
                            angular.forEach($localStorage.currentUser.authorities, function(role) {
                               if(permission == role.authority) {
                                   found = true;
                                   deferred.resolve({type: 'success', text: 'User has right role.'});
                               }
                            });
                        });

                        if(!found)
                            deferred.reject({type: 'danger', text: 'User has NOT permissions.'});
                    }
                }
                return deferred.promise;
            },
            canAccessResource: function(view, params) {
                var policy = policies[view];

                if(policy.restricted == undefined) {
                    return true;
                } else if(policy.restricted && isAuthenticated() && params.id != undefined) {

                    if($localStorage.currentUser.id == params.id || hasRole('ADMIN')) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            },
            login: function (user, callback) {
                $http.post('/api/auth/login', user)
                    .then(function (response) {
                        // login successful if there's a token in the response
                        if (response.data.token) {
                            // store username and token in local storage to keep user logged in between page refreshes
                            $localStorage.currentUser = {
                                username: user.username,
                                token: response.data.token,
                                refresh: response.data.refreshToken
                            };

                            // add jwt token to auth header for all requests made by the $http service
                            $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + response.data.token;

                            //Read current user data (roles)
                            $http.get('/api/me').then(function(response) {
                                $localStorage.currentUser.id = response.data.id;
                                $localStorage.currentUser.authorities = response.data.authorities;
                                // execute callback with true to indicate successful login
                                callback(true);
                            });
                        } else {
                            // execute callback with false to indicate failed login
                            callback(false);
                        }
                    }, function(error) {
                        callback(false);
                    });
            },
            logout: function() {
                // remove user from local storage and clear http auth header
                delete $localStorage.currentUser;
                $http.defaults.headers.common['X-Authorization'] = '';
            },
            signup: function(user, callback) {
                $http.post('/api/auth/signup', user)
                    .then(function (response) {
                        if(response.status == 200) {
                            callback(true);
                        } else {
                            callback(false);
                        }
                    }, function(error) {
                        callback(false);
                    });
            }
        };
    });
