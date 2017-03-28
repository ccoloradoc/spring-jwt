package com.colorado.jwt.models;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Created by colorado on 28/03/17.
 */
@Entity
public class User extends AbstractDomain {

    private String userName;
    @Transient
    private String password;
    private String encodedPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
}
