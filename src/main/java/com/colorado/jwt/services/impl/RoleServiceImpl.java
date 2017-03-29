package com.colorado.jwt.services.impl;

import com.colorado.jwt.models.Role;
import com.colorado.jwt.repositories.RoleRepository;
import com.colorado.jwt.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 28/03/17.
 */
@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roleList = new ArrayList<>();

        roleRepository.findAll().forEach(roleList::add);

        return roleList;
    }

    @Override
    public Role findById(int id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role saveOrUpdate(Role object) {
        return roleRepository.save(object);
    }

    @Override
    public void delete(int id) {
        roleRepository.delete(id);
    }
}
