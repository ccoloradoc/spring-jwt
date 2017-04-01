package com.colorado.jwt.controllers;

import com.colorado.jwt.models.Role;
import com.colorado.jwt.models.User;
import com.colorado.jwt.security.auth.JwtAuthenticationToken;
import com.colorado.jwt.security.commons.AuthUtils;
import com.colorado.jwt.services.RoleService;
import com.colorado.jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 31/03/17.
 */
@RestController
@RequestMapping("/api")
public class AdminController {
    private UserService userService;

    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("/role")
    public ResponseEntity<?> findAllRoles(JwtAuthenticationToken token) {
        List<Role> roleList = new ArrayList<>();
        UserDetails userDetails = (UserDetails) token.getPrincipal();

        if(AuthUtils.hasAuthority(userDetails, AuthUtils.ADMIN, AuthUtils.MANAGER)) {
            roleList = roleService.findAll();
            return new ResponseEntity<>(roleList, HttpStatus.OK);
        }

        return new ResponseEntity<>(roleList, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping("/user")
    public ResponseEntity<?> findAll(JwtAuthenticationToken token) {
        List<User> userList = new ArrayList<>();
        UserDetails userDetails = (UserDetails) token.getPrincipal();

        if(AuthUtils.hasAuthority(userDetails, AuthUtils.ADMIN, AuthUtils.MANAGER)) {
            userList = userService.findAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }

        return new ResponseEntity<>(userList, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping("/user/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id, JwtAuthenticationToken token) {
        UserDetails userDetails = (UserDetails) token.getPrincipal();

        if(AuthUtils.hasAuthority(userDetails, AuthUtils.ADMIN, AuthUtils.MANAGER)) {
            User user = userService.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody User user, JwtAuthenticationToken token) {
        UserDetails userDetails = (UserDetails) token.getPrincipal();

        if(AuthUtils.hasAuthority(userDetails, AuthUtils.ADMIN, AuthUtils.MANAGER)) {
            User savedUser = userService.saveOrUpdate(user);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody User user, JwtAuthenticationToken token) {
        UserDetails userDetails = (UserDetails) token.getPrincipal();

        if(AuthUtils.hasAuthority(userDetails, AuthUtils.ADMIN, AuthUtils.MANAGER)) {
            User savedUser = userService.saveOrUpdate(user);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id, JwtAuthenticationToken token) {
        UserDetails userDetails = (UserDetails) token.getPrincipal();

        if(AuthUtils.hasAuthority(userDetails, AuthUtils.ADMIN, AuthUtils.MANAGER)) {
            userService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}
