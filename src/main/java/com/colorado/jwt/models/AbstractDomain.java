package com.colorado.jwt.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by colorado on 28/03/17.
 */
@MappedSuperclass
public class AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private Date createdDate;
    private Date updatedDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @PrePersist
    @PreUpdate
    public void timestamps() {
        updatedDate = new Date();
        if(createdDate == null) {
            createdDate = new Date();
        }
    }
}
