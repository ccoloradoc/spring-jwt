package com.colorado.jwt.bootstrap;

import com.colorado.jwt.models.Role;
import com.colorado.jwt.models.Timezone;
import com.colorado.jwt.models.User;
import com.colorado.jwt.services.RoleService;
import com.colorado.jwt.services.TimezoneService;
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

    private TimezoneService timezoneService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setTimezoneService(TimezoneService timezoneService) {
        this.timezoneService = timezoneService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createRoles();
        User user = createUser("colorado@toptal.com", "pass1word", "MANAGER");
        addTimezones(user, "Madrid", "Spain", "ES","Europe/Madrid", "7200");
        addTimezones(user, "New York", "USA", "US","America/New_York", "-14400");
    }

    private void addTimezones(User user, String name, String country, String countryCode, String zone, String offset) {
        Timezone timezone = new Timezone();
        timezone.setUser(user);
        timezone.setName(name);
        timezone.setCountry(country);
        timezone.setCountryCode(countryCode);
        timezone.setZoneName(zone);
        timezone.setGmtOffset(offset);

        timezoneService.saveOrUpdate(timezone);
    }

    private User createUser(String username, String password, String roleName) {
        Role role = roleService.findByName(roleName);
        User user = new User(username, password);
        user.addRole(role);
        return userService.saveOrUpdate(user);
    }

    private void createRoles() {
        roleService.saveOrUpdate(new Role("USER"));
        roleService.saveOrUpdate(new Role("MANAGER"));
        roleService.saveOrUpdate(new Role("ADMIN"));
    }
}
