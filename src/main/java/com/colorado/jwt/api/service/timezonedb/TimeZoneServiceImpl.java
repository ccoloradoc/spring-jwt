package com.colorado.jwt.api.service.timezonedb;

import com.colorado.jwt.api.dto.TimezoneDto;
import com.colorado.jwt.api.dto.TimezoneResponse;
import com.colorado.jwt.api.service.ApiService;
import com.colorado.jwt.api.service.TimeZoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by colorado on 29/03/17.
 */
public class TimeZoneServiceImpl extends ApiService implements TimeZoneService {
    public static final String ZONE = "zone";
    public static final String COUNTRY = "country";

    @Override
    public List<TimezoneDto> findTimezoneByZone(String zone) {
        HashMap<String, String> params = new HashMap<>();
        params.put(ZONE, "*" + zone + "*");

        return findTimezone(params);
    }

    @Override
    public List<TimezoneDto> findTimezoneByCountry(String country) {
        HashMap<String, String> params = new HashMap<>();
        params.put(COUNTRY, country);

        return findTimezone(params);
    }

    private List<TimezoneDto> findTimezone(HashMap<String, String> params) {
        ResponseEntity<TimezoneResponse> responseEntity =
                getRestTemplate()
                        .getForEntity(getEndpoint(params), TimezoneResponse.class);
        TimezoneResponse object = responseEntity.getBody();

        return object.getZones();
    }
}
