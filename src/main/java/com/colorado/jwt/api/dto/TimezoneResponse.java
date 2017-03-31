package com.colorado.jwt.api.dto;

import com.colorado.jwt.dto.TimezoneDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 29/03/17.
 */
public class TimezoneResponse {
    private String status;
    private String message;
    private List<TimezoneDto> zones = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TimezoneDto> getZones() {
        return zones;
    }

    public void setZones(List<TimezoneDto> zones) {
        this.zones = zones;
    }
}
