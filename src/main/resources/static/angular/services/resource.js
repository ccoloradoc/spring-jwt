/**
 * Created by colorado on 30/03/17.
 */
angular.module('app')
    .factory('Resource', ['$http','$localStorage', function($http, $localStorage) {
        $http.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        return {
            country: function(callback) {
                $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                $http.get('/api/country').then(function(response) {
                    callback(response.data);
                });
            },
            timezone: function(params, callback) {
                $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                $http({
                    url: '/api/timezone',
                    method: 'GET',
                    params: params
                }).then(function(response) {
                    callback(response.data);
                });
            },
            save: function (userId, timezone, callback) {
                $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                $http.post('/api/user/' + userId + '/timezone', timezone)
                    .then(function(response) {
                        callback(response.data);
                    });
            },
            update: function (userId, timezone, callback) {
                $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                $http.put('/api/user/' + userId + '/timezone/' + timezone.id, timezone)
                    .then(function(response) {
                        callback(response.data);
                    });
            },
            getTimezones: function(userId, callback) {
                $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                $http.get('/api/user/' + userId + '/timezone')
                    .then(function(response) {
                        callback(response.data);
                    });
            },
            deleteTimezone: function (userId, timezone, callback) {
                $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                $http.delete('/api/user/' + userId + '/timezone/' + timezone.id)
                    .then(function(response) {
                        callback(response.data);
                    });
            },
            getTimezone: function(userId, timezoneId, callback) {
                $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + $localStorage.currentUser.token;
                $http.get('/api/user/' + userId + '/timezone/' + timezoneId).then(function(response) {
                    callback(response.data);
                });
            }
        };
    }]);
