/**
 * Created by colorado on 30/03/17.
 */
angular.module('app')
    .filter('gmt', function() {
        return function(input) {
            input = input || '';
            return 'GMT ' + (input < 0 ? '-' : '+') + (input/3600);
        };
    })