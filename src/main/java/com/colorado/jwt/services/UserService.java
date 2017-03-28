package com.colorado.jwt.services;

import com.colorado.jwt.models.User;

/**
 * Created by colorado on 28/03/17.
 */
public interface UserService extends CrudService<User> {
    User findByUserName(String userName);
}
