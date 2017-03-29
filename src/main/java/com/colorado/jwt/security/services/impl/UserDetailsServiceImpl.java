package com.colorado.jwt.security.services.impl;

import com.colorado.jwt.models.User;
import com.colorado.jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by colorado on 28/03/17.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    private Converter<User, UserDetails> userUserDetailsConverter;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserUserDetailsConverter(Converter<User, UserDetails> userUserDetailsConverter) {
        this.userUserDetailsConverter = userUserDetailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userUserDetailsConverter.convert(userService.findByUserName(userName));
    }
}