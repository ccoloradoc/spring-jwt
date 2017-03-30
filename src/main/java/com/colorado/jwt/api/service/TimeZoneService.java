package com.colorado.jwt.api.service;

import com.colorado.jwt.api.dto.TimezoneDto;

import java.util.List;

/**
 * Created by colorado on 29/03/17.
 */
public interface TimeZoneService {
    List<TimezoneDto> findTimezoneByZone(String zone);
    List<TimezoneDto> findTimezoneByCountry(String country);
}
