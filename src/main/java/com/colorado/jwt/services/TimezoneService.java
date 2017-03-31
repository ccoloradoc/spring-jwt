package com.colorado.jwt.services;

import com.colorado.jwt.models.Timezone;

import java.util.List;

/**
 * Created by colorado on 30/03/17.
 */
public interface TimezoneService extends CrudService<Timezone> {
    List<Timezone> findByUserId(Integer id);
}
