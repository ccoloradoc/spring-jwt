package com.colorado.jwt.converters;

import com.colorado.jwt.dto.TimeZoneRequest;
import com.colorado.jwt.models.Timezone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by colorado on 30/03/17.
 */
@Component
public class TimeZoneRequestToTimezoneConverter implements Converter<TimeZoneRequest, Timezone> {
    @Override
    public Timezone convert(TimeZoneRequest timeZoneRequest) {
        Timezone timezone = new Timezone();
        timezone.setId(timeZoneRequest.getId());
        timezone.setCountry(timeZoneRequest.getCountry());
        timezone.setCountryCode(timeZoneRequest.getBaseTimeZone().getCountryCode());
        timezone.setName(timeZoneRequest.getName());
        timezone.setZoneName(timeZoneRequest.getBaseTimeZone().getZoneName());
        timezone.setGmtOffset(timeZoneRequest.getBaseTimeZone().getGmtOffset());

        return timezone;
    }
}
