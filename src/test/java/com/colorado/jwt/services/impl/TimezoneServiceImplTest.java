package com.colorado.jwt.services.impl;

import com.colorado.jwt.models.Timezone;
import com.colorado.jwt.models.User;
import com.colorado.jwt.services.TimezoneService;
import com.colorado.jwt.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by colorado on 1/04/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TimezoneServiceImplTest {
    private TimezoneService timezoneService;

    private UserService userService;

    @Autowired
    public void setTimezoneService(TimezoneService timezoneService) {
        this.timezoneService = timezoneService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void findAll() throws Exception {
        List<Timezone> timezoneList = timezoneService.findAll();

        assert timezoneList != null;
        assert timezoneList.size() == 2;
    }

    @Test
    public void findById() throws Exception {
        Integer timezoneId = 1;
        Timezone timezone = timezoneService.findById(timezoneId);

        assert timezone != null;
        assert timezone.getId() == timezoneId;
        assert timezone.getName().equalsIgnoreCase("Madrid");
        assert timezone.getCountry().equalsIgnoreCase("Spain");
        assert timezone.getCountryCode().equalsIgnoreCase("ES");
        assert timezone.getZoneName().equalsIgnoreCase("Europe/Madrid");
        assert timezone.getGmtOffset().equalsIgnoreCase("7200");

    }

    @Test
    public void saveOrUpdate() throws Exception {
        User user = userService.findById(1);
        Timezone timezone = mockTimezone();
        timezone.setUser(user);

        Timezone savedTimezone = timezoneService.saveOrUpdate(timezone);

        assert savedTimezone != null;
        assert savedTimezone.getName().equalsIgnoreCase(timezone.getName());
        assert savedTimezone.getCountry().equalsIgnoreCase(timezone.getCountry());
        assert savedTimezone.getCountryCode().equalsIgnoreCase(timezone.getCountryCode());
        assert savedTimezone.getZoneName().equalsIgnoreCase(timezone.getZoneName());
        assert savedTimezone.getGmtOffset().equalsIgnoreCase(timezone.getGmtOffset());

        timezoneService.delete(savedTimezone.getId());
    }

    @Test
    public void delete() throws Exception {
        User user = userService.findById(1);
        Timezone timezone = mockTimezone();
        timezone.setUser(user);

        Timezone savedTimezone = timezoneService.saveOrUpdate(timezone);

        assert savedTimezone != null;
        assert savedTimezone.getName().equalsIgnoreCase(timezone.getName());
        assert savedTimezone.getCountry().equalsIgnoreCase(timezone.getCountry());
        assert savedTimezone.getCountryCode().equalsIgnoreCase(timezone.getCountryCode());
        assert savedTimezone.getZoneName().equalsIgnoreCase(timezone.getZoneName());
        assert savedTimezone.getGmtOffset().equalsIgnoreCase(timezone.getGmtOffset());

        timezoneService.delete(savedTimezone.getId());

        Timezone deletedTimezone = timezoneService.findById(savedTimezone.getId());

        assert deletedTimezone == null;
    }

    @Test
    public void findByUserId() throws Exception {
        Integer userId = 1;
        List<Timezone> timezoneList = timezoneService.findByUserId(userId);

        assert timezoneList != null;
        assert timezoneList.size() == 2;
    }

    private Timezone mockTimezone() {
        Timezone timezone = new Timezone();
        timezone.setName("San Luis Potosi");
        timezone.setCountry("Mexico");
        timezone.setCountryCode("MX");
        timezone.setZoneName("America/Mexico");
        timezone.setGmtOffset("-3600");
        return timezone;
    }

}