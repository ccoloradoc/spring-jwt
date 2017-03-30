package com.colorado.jwt.controllers;

import com.colorado.jwt.api.dto.TimezoneDto;
import com.colorado.jwt.api.service.TimeZoneService;
import com.colorado.jwt.models.Country;
import com.colorado.jwt.security.auth.JwtAuthenticationToken;
import com.colorado.jwt.services.CountryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by colorado on 28/03/17.
 */
@RestController
public class ResourceController {
    private CountryService countryService;

    private TimeZoneService timeZoneService;

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Autowired
    public void setTimeZoneService(TimeZoneService timeZoneService) {
        this.timeZoneService = timeZoneService;
    }

    @RequestMapping("/api/country")
    public List<Country> findResource() {
        return countryService.findAll();
    }

    @RequestMapping("/api/timezone")
    public List<TimezoneDto> findTimeZone(@RequestParam(required = false) String zone, @RequestParam(required = false) String country) {
        HashMap<String, String> params = new HashMap<>();

        if(StringUtils.isNotEmpty(zone))
            params.put(TimeZoneService.ZONE, zone);

        if(StringUtils.isNotEmpty(country))
            params.put(TimeZoneService.COUNTRY, country);

        return timeZoneService.findTimezone(params);
    }

}
