package com.colorado.jwt.dto;

/**
 * Created by colorado on 30/03/17.
 */
public class TimeZoneRequest {
    private Integer id;
    private String name;
    private String country;
    private TimezoneDto baseTimeZone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public TimezoneDto getBaseTimeZone() {
        return baseTimeZone;
    }

    public void setBaseTimeZone(TimezoneDto baseTimeZone) {
        this.baseTimeZone = baseTimeZone;
    }
}
