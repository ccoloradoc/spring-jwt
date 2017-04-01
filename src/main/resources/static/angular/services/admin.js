/**
 * Created by colorado on 31/03/17.
 */
angular.module('app')
    .factory('Admin', ['$http','$localStorage', function($http, $localStorage) {
        $http.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        var url = '/api/user';
        return {
            User: {
                query: function(callback) {
                    $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                    $http.get(url)
                        .then(function(response) {
                            callback(response.data);
                        });
                },
                get: function(userId, callback) {
                    $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                    $http.get(url + '/' + userId).then(function(response) {
                        callback(response.data);
                    });
                },
                save: function (user, callback) {
                    $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                    $http.post(url, user)
                        .then(function(response) {
                            callback(response.data);
                        });
                },
                update: function (user, callback) {
                    $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                    $http.put(url + '/' + user.id, user)
                        .then(function(response) {
                            callback(response.data);
                        });
                },
                remove: function (userId, callback) {
                    $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                    $http.delete(url + '/' + userId)
                        .then(function(response) {
                            callback(response.data);
                        });
                }
            },
            Role: {
                query: function(callback) {
                    $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                    $http.get('/api/role')
                        .then(function(response) {
                            callback(response.data);
                        });
                }
            }
        };
    }]);
