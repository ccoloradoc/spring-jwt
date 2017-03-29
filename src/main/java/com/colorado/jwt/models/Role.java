package com.colorado.jwt.models;

import javax.persistence.Entity;

/**
 * Created by colorado on 28/03/17.
 */
@Entity
public class Role extends AbstractDomain {
    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
