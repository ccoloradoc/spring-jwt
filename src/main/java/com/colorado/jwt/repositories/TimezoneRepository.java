package com.colorado.jwt.repositories;

import com.colorado.jwt.models.Timezone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by colorado on 30/03/17.
 */
public interface TimezoneRepository extends CrudRepository<Timezone, Integer> {
    List<Timezone> findByUserId(Integer userId);
}
