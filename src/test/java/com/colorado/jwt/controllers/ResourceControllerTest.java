package com.colorado.jwt.controllers;

import com.colorado.jwt.api.service.TimeZoneService;
import com.colorado.jwt.dto.TimezoneDto;
import com.colorado.jwt.models.Country;
import com.colorado.jwt.models.Timezone;
import com.colorado.jwt.services.CountryService;
import com.colorado.jwt.util.Utils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by colorado on 2/04/17.
 */
public class ResourceControllerTest {
    @InjectMocks
    private ResourceController resourceController;

    @Mock
    private CountryService countryService;

    @Mock
    private TimeZoneService timeZoneService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        //Initialize mocks
        MockitoAnnotations.initMocks(this);
        //Setup
        mockMvc = MockMvcBuilders.standaloneSetup(resourceController, countryService, timeZoneService).build();
    }

    @Test
    public void findResource() throws Exception {
        when(countryService.findAll()).thenReturn(Arrays.asList(
                mockCountry(1, "Mexico", "MX"),
                mockCountry(2, "Spain", "ES")));

        mockMvc.perform(get("/api/country"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(Utils.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].countryName", is("Mexico")))
                .andExpect(jsonPath("$[0].countryCode", is("MX")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].countryName", is("Spain")))
                .andExpect(jsonPath("$[1].countryCode", is("ES")));

        verify(countryService, times(1)).findAll();
        verifyNoMoreInteractions(countryService);
    }

    @Test
    public void findTimeZone() throws Exception {
        when(timeZoneService.findTimezone(any())).thenReturn(Arrays.asList(
                mockTimezone("Mexico", "MX", "America/Monterrey","3600"),
                mockTimezone("Mexico", "MX", "America/Merida","3600")));

        mockMvc.perform(get("/api/timezone"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(Utils.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].countryName", is("Mexico")))
                .andExpect(jsonPath("$[0].countryCode", is("MX")))
                .andExpect(jsonPath("$[0].zoneName", is("America/Monterrey")))
                .andExpect(jsonPath("$[0].gmtOffset", is("3600")))
                .andExpect(jsonPath("$[1].countryName", is("Mexico")))
                .andExpect(jsonPath("$[1].countryCode", is("MX")))
                .andExpect(jsonPath("$[1].zoneName", is("America/Merida")))
                .andExpect(jsonPath("$[1].gmtOffset", is("3600")));

        verify(timeZoneService, times(1)).findTimezone(any());
        verifyNoMoreInteractions(timeZoneService);
    }

    private Country mockCountry(Integer id, String countryName, String countryCode) {
        Country country = new Country();

        country.setId(id);
        country.setCountryName(countryName);
        country.setCountryCode(countryCode);

        return country;
    }

    private TimezoneDto mockTimezone(String countryName, String countryCode, String zoneName, String gmtOffset) {
        TimezoneDto timezone = new TimezoneDto();

        timezone.setCountryName(countryName);
        timezone.setCountryCode(countryCode);
        timezone.setZoneName(zoneName);
        timezone.setGmtOffset(gmtOffset);

        return timezone;
    }

}