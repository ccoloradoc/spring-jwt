package com.colorado.jwt.services.impl;

import com.colorado.jwt.models.User;
import com.colorado.jwt.repositories.UserRepository;
import com.colorado.jwt.security.services.SecurityService;
import com.colorado.jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 28/03/17.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private SecurityService securityService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        userRepository.findAll().forEach(userList::add);

        return userList;
    }

    @Override
    public User findById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User saveOrUpdate(User user) {
        if(user.getPassword() != null) {
            user.setEncodedPassword(securityService.encryptString(user.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
