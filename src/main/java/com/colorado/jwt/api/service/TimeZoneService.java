package com.colorado.jwt.api.service;

import com.colorado.jwt.dto.TimezoneDto;

import java.util.List;
import java.util.Map;

/**
 * Created by colorado on 29/03/17.
 */
public interface TimeZoneService {
    String ZONE = "zone";
    String COUNTRY = "country";

    List<TimezoneDto> findTimezone(Map<String, String> params);
}
