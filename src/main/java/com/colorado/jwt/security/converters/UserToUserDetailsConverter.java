package com.colorado.jwt.security.converters;

import com.colorado.jwt.models.Role;
import com.colorado.jwt.models.User;
import com.colorado.jwt.security.commons.dto.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 28/03/17.
 */
@Component
public class UserToUserDetailsConverter implements Converter<User, UserDetails> {

    @Override
    public UserDetails convert(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(user.getUserName());
        userDetails.setPassword(user.getEncodedPassword());
        userDetails.setEnabled(true);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoleList().forEach((Role role) -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
