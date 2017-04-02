package com.colorado.jwt.services.impl;

import com.colorado.jwt.models.User;
import com.colorado.jwt.security.services.SecurityService;
import com.colorado.jwt.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by colorado on 1/04/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    private UserService userService;

    private SecurityService securityService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Test
    public void findAll() throws Exception {
        List<User> userList = userService.findAll();

        assert userList != null;
        assert userList.size() == 1;
    }

    @Test
    public void findById() throws Exception {
        Integer userId = 1;
        User user = userService.findById(userId);

        assert user != null;
        assert user.getUserName().equalsIgnoreCase("colorado@toptal.com");
    }

    @Test
    public void saveOrUpdate() throws Exception {
        User user = mockUser("cervantes@toptal.com", "pass1word");
        User savedUser = userService.saveOrUpdate(user);

        assert savedUser != null;
        assert savedUser.getUserName().equalsIgnoreCase(user.getUserName());
        assert securityService.checkPassword(user.getPassword(), savedUser.getEncodedPassword());

        userService.delete(savedUser.getId());
    }

    @Test
    public void delete() throws Exception {
        User user = mockUser("cervantes@toptal.com", "pass1word");
        User savedUser = userService.saveOrUpdate(user);

        assert savedUser != null;
        assert savedUser.getUserName().equalsIgnoreCase(user.getUserName());
        assert securityService.checkPassword(user.getPassword(), savedUser.getEncodedPassword());

        userService.delete(savedUser.getId());

        User deletedUser = userService.findByUserName(user.getUserName());

        assert deletedUser == null;
    }

    @Test
    public void findByUserName() throws Exception {
        String userName = "colorado@toptal.com";
        User user = userService.findByUserName(userName);

        assert user != null;
        assert user.getUserName().equalsIgnoreCase(userName);
    }

    private User mockUser(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        return user;
    }

}