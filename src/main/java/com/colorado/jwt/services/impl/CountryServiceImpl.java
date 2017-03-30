package com.colorado.jwt.services.impl;

import com.colorado.jwt.models.Country;
import com.colorado.jwt.repositories.CountryRepository;
import com.colorado.jwt.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 30/03/17.
 */
@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        List<Country> countryList = new ArrayList<>();

        countryRepository.findAll().forEach(countryList::add);

        return countryList;
    }

    @Override
    public Country findById(int id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country saveOrUpdate(Country object) {
        return countryRepository.saveOrUpdate(object);
    }

    @Override
    public void delete(int id) {
        countryRepository.delete(id);
    }
}
