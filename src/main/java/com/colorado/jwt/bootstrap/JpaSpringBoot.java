package com.colorado.jwt.bootstrap;

import com.colorado.jwt.models.Role;
import com.colorado.jwt.models.User;
import com.colorado.jwt.services.RoleService;
import com.colorado.jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by colorado on 28/03/17.
 */
@Component
public class JpaSpringBoot implements ApplicationListener<ContextRefreshedEvent> {

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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createRoles();
        createUser("colorado", "pass1word", "USER");
    }

    private void createUser(String username, String password, String roleName) {
        Role role = roleService.findByName(roleName);
        User user = new User(username, password);
        user.addRole(role);
        userService.saveOrUpdate(user);
    }

    private void createRoles() {
        roleService.saveOrUpdate(new Role("USER"));
        roleService.saveOrUpdate(new Role("MANAGER"));
        roleService.saveOrUpdate(new Role("ADMIN"));
    }
}
