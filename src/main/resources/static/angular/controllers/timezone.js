/**
 * Created by colorado on 30/03/17.
 */
angular.module('app')
    .controller('NewTimeZoneController', ['$scope', '$timeout', '$routeParams', 'Resource',
        function($scope, $timeout, $routeParams, Resource) {
        console.log($routeParams);

        $scope.timezones = [];

        $scope.timezone = {
            baseTimeZone: {
                gmtOffset: 0
            }
        };

        //Loading country catalog
        Resource.country(function(countries) {
            $scope.country = {};
            $scope.countryDropdown = {};
            angular.forEach(countries, function(item) {
                $scope.country[item.countryName] = item.countryCode;
                $scope.countryDropdown[item.countryName] =
                    'https://www.translatorscafe.com/cafe/images/flags/' + item.countryCode + '.gif';
            });

            $('input#autocomplete-country').autocomplete({
                data: $scope.countryDropdown,
                limit: 10,
                onAutocomplete: function(countryName) {
                    $scope.updateTimezone($scope.country[countryName]);
                },
                minLength: 0
            });

        });

        $scope.updateTimezone = function(countryCode) {
            Resource.timezone({country: countryCode }, function(timezones) {
                $scope.timezones = timezones;
                $scope.timezone.baseTimeZone = timezones[1];

                //Initialize select after timezones are displayed on digester
                $timeout(function(){
                    $('select').material_select();
                },0,false);
            })
        };

        $scope.submit = function() {
            console.log($scope.timezone);
        }

        $( document ).ready(function(){
            $('select').material_select();
        });
    }]);