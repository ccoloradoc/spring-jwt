package com.colorado.jwt.security.config;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    public StrongPasswordEncryptor strongEntryptor() {
        return new StrongPasswordEncryptor();
    }

    @Bean
    public PasswordEncoder passwordEncoder(StrongPasswordEncryptor strongPasswordEncryptor) {
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        passwordEncoder.setPasswordEncryptor(strongPasswordEncryptor);
        return passwordEncoder;
    }
}
