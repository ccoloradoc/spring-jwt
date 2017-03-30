package com.colorado.jwt.api.service.timezonedb;

import com.colorado.jwt.api.dto.TimezoneDto;
import com.colorado.jwt.api.service.TimeZoneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
        List<TimezoneDto> timezoneDtoList = timeZoneService.findTimezoneByZone(zone);

        assert timezoneDtoList != null;
        assert timezoneDtoList.size() == 9;

        for(TimezoneDto timezoneDto : timezoneDtoList) {
            assert timezoneDto.getZoneName().toLowerCase().contains(zone.toLowerCase());
        }
    }

    @Test
    public void findTimezoneByCountry() throws Exception {
        String countryCode = "MX";
        List<TimezoneDto> timezoneDtoList = timeZoneService.findTimezoneByCountry(countryCode);

        assert timezoneDtoList != null;
        assert timezoneDtoList.size() == 11;

        for(TimezoneDto timezoneDto : timezoneDtoList) {
            assertEquals("Error", timezoneDto.getCountryCode(), countryCode);
        }
    }

}