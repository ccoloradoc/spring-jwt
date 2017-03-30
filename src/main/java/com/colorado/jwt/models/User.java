package com.colorado.jwt.models;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 28/03/17.
 */
@Entity
public class User extends AbstractDomain {

    private String userName;
    @Transient
    private String password;
    private String encodedPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="USER_ROLE",
            joinColumns = @JoinColumn( name="USER_ID"),
            inverseJoinColumns = @JoinColumn( name="ROLE_ID")
    )
    private List<Role> roleList = new ArrayList<>();

    public User() {}

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

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

    public void addRole(Role role) {
        roleList.add(role);
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
