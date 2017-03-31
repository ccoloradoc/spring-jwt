package com.colorado.jwt.api.service.timezonedb;

import com.colorado.jwt.dto.TimezoneDto;
import com.colorado.jwt.api.service.TimeZoneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by colorado on 29/03/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TimeZoneServiceImplTest {

    private TimeZoneService timeZoneService;

    @Autowired
    public void setTimeZoneService(TimeZoneService timeZoneService) {
        this.timeZoneService = timeZoneService;
    }

    @Test
    public void findTimezone() throws Exception {
        String zone = "Mon";
        Map<String, String> params = new HashMap<>();
        params.put(TimeZoneService.ZONE, "*" + zone + "*");
        List<TimezoneDto> timezoneDtoList = timeZoneService.findTimezone(params);

        assert timezoneDtoList != null;
        assert timezoneDtoList.size() == 9;

        for(TimezoneDto timezoneDto : timezoneDtoList) {
            assert timezoneDto.getZoneName().toLowerCase().contains(zone.toLowerCase());
        }
    }

    @Test
    public void findTimezoneByCountry() throws Exception {
        String countryCode = "MX";
        Map<String, String> params = new HashMap<>();
        params.put(TimeZoneService.COUNTRY, countryCode);
        List<TimezoneDto> timezoneDtoList = timeZoneService.findTimezone(params);
        assert timezoneDtoList != null;
        assert timezoneDtoList.size() == 11;

        for(TimezoneDto timezoneDto : timezoneDtoList) {
            assertEquals("Error", timezoneDto.getCountryCode(), countryCode);
        }
    }

}