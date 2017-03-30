package com.colorado.jwt.security.endpoint;

import com.colorado.jwt.models.Role;
import com.colorado.jwt.models.User;
import com.colorado.jwt.security.commons.dto.ErrorCode;
import com.colorado.jwt.security.commons.dto.ErrorResponse;
import com.colorado.jwt.security.commons.dto.LoginRequest;
import com.colorado.jwt.security.converters.LoginRequestToUserConverter;
import com.colorado.jwt.security.converters.UserToUserDetailsConverter;
import com.colorado.jwt.services.RoleService;
import com.colorado.jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Created by colorado on 29/03/17.
 */
@RestController
public class SignUpEntryPoint {
    private UserService userService;

    private RoleService roleService;

    private LoginRequestToUserConverter loginRequestToUserConverter;

    private UserToUserDetailsConverter userToUserDetailsConverter;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setLoginRequestToUserConverter(LoginRequestToUserConverter loginRequestToUserConverter) {
        this.loginRequestToUserConverter = loginRequestToUserConverter;
    }

    @Autowired
    public void setUserToUserDetailsConverter(UserToUserDetailsConverter userToUserDetailsConverter) {
        this.userToUserDetailsConverter = userToUserDetailsConverter;
    }

    @RequestMapping(value="/api/auth/signup", method= RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> signUp(@RequestBody LoginRequest loginRequest) {
        if(userService.findByUserName(loginRequest.getUsername()) == null) {
            Role role = roleService.findByName("USER");
            User user = loginRequestToUserConverter.convert(loginRequest);
            user.addRole(role);
            user = userService.saveOrUpdate(user);
            UserDetails userDetails = userToUserDetailsConverter.convert(user);
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        } else {
            ErrorResponse errorResponse =
                    ErrorResponse.of("Duplicated username", ErrorCode.EXISTENT_USER, HttpStatus.CONFLICT);
            return new ResponseEntity<Object>(errorResponse, HttpStatus.CONFLICT);
        }



    }
}
