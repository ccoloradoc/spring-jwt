/**
 * Created by colorado on 30/03/17.
 */
angular.module('app')
    .directive('clock', function() {
        return {
            restrict: 'AE',
            templateUrl: 'angular/templates/directives/clock.html',
            replace: true,
            scope: {
                timezone: '='
            },
            controller: ['$scope', 'Gmt',
                function($scope, Gmt) {
                    var radius = 6;
                    var clockEvt;

                    $scope.$watch('timezone', function(newVal) {
                        if(newVal != undefined) {
                            var currentTime = Gmt.time(newVal.gmtOffset);
                            var second = currentTime.getSeconds() * radius;
                            var minute = currentTime.getMinutes() * radius + Math.floor(second / (radius * 10) * 10) / 10;
                            var hour = currentTime.getHours() * radius * 5 + Math.floor(minute / (radius * 2) * 10) / 10;

                            if(clockEvt != undefined)
                                clearInterval(clockEvt);

                            clockEvt = setClockHands(newVal.id || 1, second, minute, hour);
                        }
                    });

                    $(document).ready(function() {
                        for(var i=0; i < 60; i++)
                            $('.clock__marks').append('<li></li>');
                    });


                    function setClockHands(id, second, minute, hour){
                        var $clock = $('#clock' + id);
                        var secondElm = $clock.find('.clock__hand--second');
                        var minuteElm = $clock.find('.clock__hand--minute');
                        var hourElm = $clock.find('.clock__hand--hour');

                        secondElm.css('transform', 'rotate(' + second + 'deg)');
                        minuteElm.css('transform', 'rotate(' + minute + 'deg)');
                        hourElm.css('transform', 'rotate(' + hour + 'deg)');

                        var interval = 1000; //1 second
                        var before = Gmt.time($scope.timezone.gmtOffset);

                        if($scope.timezone != undefined)
                        return setInterval(function(){
                            var now = Gmt.time($scope.timezone.gmtOffset);
                            var elapsedTime = now.getTime() - before.getTime(); //Fix calculating in inactive tabs
                            var delay = Math.round(elapsedTime/interval);

                            second += radius * delay;
                            for(var i=0; i<delay; i++){
                                if( ((second - i) * radius) % (radius * 5) === 0 ){
                                    minute += 0.5;
                                    if( minute % radius === 0 ){
                                        hour += 0.5;
                                    }
                                }
                            }

                            secondElm.css('transform', 'rotate(' + second + 'deg)');
                            minuteElm.css('transform', 'rotate(' + minute + 'deg)');
                            hourElm.css('transform', 'rotate(' + hour + 'deg)');

                            before = Gmt.time($scope.timezone.gmtOffset);
                        }, interval);
                    }
                }]
        };
    });
