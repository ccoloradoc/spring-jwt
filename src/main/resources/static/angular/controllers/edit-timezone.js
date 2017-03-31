/**
 * Created by colorado on 31/03/17.
 */
angular.module('app')
    .controller('EditTimeZoneController', ['$scope', '$timeout', '$location', '$routeParams', 'Resource',
        function($scope, $timeout, $location, $routeParams, Resource) {
            $scope.id = $routeParams.id;
            $scope.timezoneId = $routeParams.timezoneId;

            Resource.getTimezone($scope.id, $scope.timezoneId, function(timezone) {
                $scope.timezone = timezone;
                $scope.updateTimezone($scope.timezone.countryCode, false);
            });

            $scope.timezones = [];

            //Loading country catalog
            Resource.country(function(countries) {
                $scope.country = {};
                $scope.countryDropdown = {};
                angular.forEach(countries, function(item) {
                    $scope.country[item.countryName] = item.countryCode;
                    $scope.countryDropdown[item.countryName] =
                        '/images/flags/' + item.countryCode + '.gif';
                });

                $('input#autocomplete-country').autocomplete({
                    data: $scope.countryDropdown,
                    limit: 10,
                    onAutocomplete: function(countryName) {
                        $scope.updateTimezone($scope.country[countryName], true);
                    },
                    minLength: 0
                });

            });

            $scope.updateTimezone = function(countryCode, newSet) {
                Resource.timezone({country: countryCode }, function(timezones) {
                    $scope.timezones = timezones;

                    if(newSet) {
                        $scope.timezone.baseTimeZone = timezones[0];
                    } else {
                        //Initialize select after timezones are displayed on digester
                        if($scope.timezone.zoneName != undefined) {
                            angular.forEach($scope.timezones, function(timezone) {
                                if(timezone.zoneName == $scope.timezone.zoneName) {
                                    $scope.timezone.baseTimeZone = timezone;
                                }
                            });
                        }
                    }

                    $timeout(function(){
                        $('select').material_select();
                    },0,false);
                })
            };

            $scope.submit = function() {
                $scope.timezone.countryCode = $scope.timezone.baseTimeZone.countryCode;
                $scope.timezone.zoneName = $scope.timezone.baseTimeZone.zoneName;
                $scope.timezone.gmtOffset = $scope.timezone.baseTimeZone.gmtOffset;
                Resource.update($scope.id, $scope.timezone, function(response) {
                    $location.path('/user/' + $scope.id);
                })
            }

            $( document ).ready(function(){
                $('select').material_select();
            });
        }]);