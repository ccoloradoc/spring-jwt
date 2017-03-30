/**
 * Created by colorado on 30/03/17.
 */
angular.module('app')
    .factory('Gmt', [function() {
        return {
            now: function() {
                var now = new Date();
                return new Date(now.valueOf() - (now.getTimezoneOffset() * 60000));
            },
            time: function(offset) {
                var now = new Date();
                return new Date(now.valueOf() - (now.getTimezoneOffset() * 60000) + (offset * 1000));
            }
        };
    }]);
