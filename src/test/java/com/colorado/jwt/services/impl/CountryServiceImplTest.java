package com.colorado.jwt.services.impl;

import com.colorado.jwt.models.Country;
import com.colorado.jwt.services.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by colorado on 1/04/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CountryServiceImplTest {
    private CountryService countryService;

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Test
    public void findAll() throws Exception {
        List<Country> countryList = countryService.findAll();

        assert countryList != null;
        assert countryList.size() == 252;
    }

    @Test
    public void findById() throws Exception {
        Country country = countryService.findById(1);

        assert country != null;
        assert country.getCountryCode().compareToIgnoreCase("AD") == 0;
        assert country.getCountryName().compareToIgnoreCase("Andorra") == 0;
    }

    @Test
    public void saveOrUpdate() throws Exception {
        Country country = mockCountry("Atlantis","AT");
        Country savedCountry = countryService.saveOrUpdate(country);

        assert country != null;
        assert country.getCountryName().equalsIgnoreCase(savedCountry.getCountryName());
        assert country.getCountryCode().equalsIgnoreCase(savedCountry.getCountryCode());

        countryService.delete(savedCountry.getId());
    }

    @Test
    public void delete() throws Exception {
        Country country = mockCountry("Atlantis","AT");
        Country savedCountry = countryService.saveOrUpdate(country);

        assert country != null;
        assert country.getCountryName().equalsIgnoreCase(savedCountry.getCountryName());
        assert country.getCountryCode().equalsIgnoreCase(savedCountry.getCountryCode());

        countryService.delete(savedCountry.getId());

        Country deletedCountry = countryService.findById(savedCountry.getId());

        assert deletedCountry == null;
    }

    private Country mockCountry(String countryName, String countryCode) {
        Country country = new Country();
        country.setCountryName(countryName);
        country.setCountryCode(countryCode);
        return country;
    }

}