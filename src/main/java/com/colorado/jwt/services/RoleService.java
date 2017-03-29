package com.colorado.jwt.services;

import com.colorado.jwt.models.Role;

/**
 * Created by colorado on 28/03/17.
 */
public interface RoleService extends CrudService<Role> {
    Role findByName(String name);
}
