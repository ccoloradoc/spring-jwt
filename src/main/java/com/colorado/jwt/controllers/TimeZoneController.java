package com.colorado.jwt.controllers;

import com.colorado.jwt.converters.TimeZoneRequestToTimezoneConverter;
import com.colorado.jwt.dto.TimeZoneRequest;
import com.colorado.jwt.models.Timezone;
import com.colorado.jwt.models.User;
import com.colorado.jwt.security.auth.JwtAuthenticationToken;
import com.colorado.jwt.security.commons.AuthUtils;
import com.colorado.jwt.services.TimezoneService;
import com.colorado.jwt.services.UserService;
import org.apache.catalina.loader.ResourceEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 30/03/17.
 */
@RestController
public class TimeZoneController {
    private UserService userService;

    private TimezoneService timezoneService;

    private TimeZoneRequestToTimezoneConverter timeZoneRequestToTimezoneConverter;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTimezoneService(TimezoneService timezoneService) {
        this.timezoneService = timezoneService;
    }

    @Autowired
    public void setTimeZoneRequestToTimezoneConverter(TimeZoneRequestToTimezoneConverter timeZoneRequestToTimezoneConverter) {
        this.timeZoneRequestToTimezoneConverter = timeZoneRequestToTimezoneConverter;
    }

    @RequestMapping("/api/user/{userId}/timezone")
    public ResponseEntity<?> findAllTimezones(@PathVariable Integer userId, JwtAuthenticationToken token) {
        List<Timezone> timezoneList = new ArrayList<>();
        UserDetails userDetails = (UserDetails) token.getPrincipal();
        User client = userService.findByUserName(userDetails.getUsername());

        //If requesting own records OR it is ADMIN
        if((client != null && client.getId() == userId) || AuthUtils.hasAuthority(userDetails, "ADMIN")) {
            timezoneList = timezoneService.findByUserId(userId);
            return new ResponseEntity<>(timezoneList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(timezoneList, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("/api/user/{userId}/timezone/{timezoneId}")
    public ResponseEntity<?> findTimezone(@PathVariable Integer userId, @PathVariable Integer timezoneId,
                                              JwtAuthenticationToken token) {
        UserDetails userDetails = (UserDetails) token.getPrincipal();
        User client = userService.findByUserName(userDetails.getUsername());

        //If requesting own records OR it is ADMIN
        if((client != null && client.getId() == userId) || AuthUtils.hasAuthority(userDetails, "ADMIN")) {
            Timezone timezone = timezoneService.findById(timezoneId);
            return new ResponseEntity<>(timezone, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/api/user/{userId}/timezone", method = RequestMethod.POST)
    public ResponseEntity<?> saveTimeZone(@PathVariable Integer userId, @RequestBody TimeZoneRequest timeZoneRequest, JwtAuthenticationToken token) {
        UserDetails userDetails = (UserDetails) token.getPrincipal();
        User client = userService.findByUserName(userDetails.getUsername());
        Timezone timezone = timeZoneRequestToTimezoneConverter.convert(timeZoneRequest);

        // If requesting saving for same user
        if(client != null && client.getId() == userId) {
            timezone.setUser(client);
            Timezone savedTimezone = timezoneService.saveOrUpdate(timezone);
            return new ResponseEntity<>(savedTimezone, HttpStatus.OK);
        } else if(AuthUtils.hasAuthority(userDetails, "ADMIN")) {
            //Requesting saving for other user
            User otherUser = userService.findById(userId);
            timezone.setUser(otherUser);
            Timezone savedTimezone = timezoneService.saveOrUpdate(timezone);
            return new ResponseEntity<>(savedTimezone, HttpStatus.OK);
        } else {
            //Saving not granted
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/api/user/{userId}/timezone/{timezoneId}", method = RequestMethod.PUT)
    public ResponseEntity<?> editTimeZone(@PathVariable Integer userId, @PathVariable Integer timezoneId, @RequestBody Timezone timezone, JwtAuthenticationToken token) {
        UserDetails userDetails = (UserDetails) token.getPrincipal();
        User client = userService.findByUserName(userDetails.getUsername());

        // If requesting saving for same user
        if(client != null && client.getId() == userId) {
            timezone.setUser(client);
            Timezone savedTimezone = timezoneService.saveOrUpdate(timezone);
            return new ResponseEntity<>(savedTimezone, HttpStatus.OK);
        } else if(AuthUtils.hasAuthority(userDetails, "ADMIN")) {
            //Requesting saving for other user
            User otherUser = userService.findById(userId);
            timezone.setUser(otherUser);
            Timezone savedTimezone = timezoneService.saveOrUpdate(timezone);
            return new ResponseEntity<>(savedTimezone, HttpStatus.OK);
        } else {
            //Saving not granted
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/api/user/{userId}/timezone/{timezoneId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer userId, @PathVariable Integer timezoneId, JwtAuthenticationToken token) {
        UserDetails userDetails = (UserDetails) token.getPrincipal();
        User client = userService.findByUserName(userDetails.getUsername());

        // If requesting deleting for same user
        if(client != null && client.getId() == userId) {
            timezoneService.delete(timezoneId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else if(AuthUtils.hasAuthority(userDetails, "ADMIN")) {
            //Requesting saving for other user
            timezoneService.delete(timezoneId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            //Saving not granted
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
