package com.colorado.jwt.repositories;

import com.colorado.jwt.models.Country;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by colorado on 30/03/17.
 */
public interface CountryRepository extends CrudRepository<Country, Integer> {

}
