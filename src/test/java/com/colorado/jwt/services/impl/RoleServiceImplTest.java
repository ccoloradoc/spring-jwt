package com.colorado.jwt.services.impl;

import com.colorado.jwt.models.Role;
import com.colorado.jwt.services.RoleService;
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
public class RoleServiceImplTest {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    public void findAll() throws Exception {
        List<Role> roleList = roleService.findAll();

        assert roleList != null;
        assert roleList.size() == 3;
    }

    @Test
    public void findById() throws Exception {
        Integer roleId = 1;
        Role role = roleService.findById(roleId);

        assert role != null;
        assert role.getId() == roleId;
        assert role.getName().equalsIgnoreCase("USER");
    }

    @Test
    public void findByName() throws Exception {
        String roleName = "USER";
        Role role = roleService.findByName(roleName);

        assert role != null;
        assert role.getName().equalsIgnoreCase(roleName);
        assert role.getId() == 1;
    }

    @Test
    public void saveOrUpdate() throws Exception {
        String roleName = "SUPER";
        Role savedRole = roleService.saveOrUpdate(mockRole(roleName));

        assert savedRole != null;
        assert savedRole.getName().equalsIgnoreCase(roleName);

        roleService.delete(savedRole.getId());
    }

    @Test
    public void delete() throws Exception {
        String roleName = "SUPER";
        Role savedRole = roleService.saveOrUpdate(mockRole(roleName));

        assert savedRole != null;
        assert savedRole.getName().equalsIgnoreCase(roleName);

        roleService.delete(savedRole.getId());

        assert roleService.findById(savedRole.getId()) == null;
    }

    private Role mockRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return role;
    }
}