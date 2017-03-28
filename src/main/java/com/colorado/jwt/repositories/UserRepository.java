package com.colorado.jwt.repositories;

import com.colorado.jwt.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by colorado on 28/03/17.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserName(String userName);
}
