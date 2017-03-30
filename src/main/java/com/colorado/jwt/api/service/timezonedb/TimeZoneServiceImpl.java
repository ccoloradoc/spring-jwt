package com.colorado.jwt.api.service.timezonedb;

import com.colorado.jwt.api.dto.TimezoneDto;
import com.colorado.jwt.api.dto.TimezoneResponse;
import com.colorado.jwt.api.service.ApiService;
import com.colorado.jwt.api.service.TimeZoneService;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by colorado on 29/03/17.
 */
public class TimeZoneServiceImpl extends ApiService implements TimeZoneService {

    @Override
    public List<TimezoneDto> findTimezone(Map<String, String> params) {
        ResponseEntity<TimezoneResponse> responseEntity =
                getRestTemplate()
                        .getForEntity(getEndpoint(params), TimezoneResponse.class);
        TimezoneResponse object = responseEntity.getBody();

        return object.getZones();
    }
}
