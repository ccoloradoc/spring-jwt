package com.colorado.jwt.services.impl;

import com.colorado.jwt.models.User;
import com.colorado.jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 28/03/17.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        userService.findAll().forEach(userList::add);

        return userList;
    }

    @Override
    public User findById(int id) {
        return userService.findById(id);
    }

    @Override
    public User saveOrUpdate(User object) {
        return userService.saveOrUpdate(object);
    }

    @Override
    public void delete(int id) {
        userService.delete(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userService.findByUserName(userName);
    }
}
