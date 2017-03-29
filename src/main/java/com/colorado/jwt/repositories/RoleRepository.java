package com.colorado.jwt.repositories;

import com.colorado.jwt.models.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by colorado on 28/03/17.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
