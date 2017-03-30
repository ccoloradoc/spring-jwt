package com.colorado.jwt.config;

import com.colorado.jwt.api.service.TimeZoneService;
import com.colorado.jwt.api.service.timezonedb.TimeZoneServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by colorado on 29/03/17.
 */
@Configuration
public class ApiServiceConfig {
    @Bean
    @ConfigurationProperties("spring.timezonedb")
    public TimeZoneService getTimeZoneService() {
        return new TimeZoneServiceImpl();
    }
}
