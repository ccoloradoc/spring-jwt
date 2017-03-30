package com.colorado.jwt.security.converters;

import com.colorado.jwt.models.User;
import com.colorado.jwt.security.commons.dto.LoginRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by colorado on 29/03/17.
 */
@Component
public class LoginRequestToUserConverter implements Converter<LoginRequest, User> {
    @Override
    public User convert(LoginRequest loginRequest) {
        User user = new User();

        user.setUserName(loginRequest.getUsername());
        user.setPassword(loginRequest.getPassword());

        return user;
    }
}
