package com.colorado.jwt.services.impl;

import com.colorado.jwt.models.Timezone;
import com.colorado.jwt.repositories.TimezoneRepository;
import com.colorado.jwt.services.TimezoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 30/03/17.
 */
@Service
public class TimezoneServiceImpl implements TimezoneService {
    private TimezoneRepository timezoneRepository;

    @Autowired
    public void setTimezoneRepository(TimezoneRepository timezoneRepository) {
        this.timezoneRepository = timezoneRepository;
    }

    @Override
    public List<Timezone> findAll() {
        List<Timezone> timezoneList = new ArrayList<>();

        timezoneRepository.findAll().forEach(timezoneList::add);

        return timezoneList;
    }

    @Override
    public Timezone findById(int id) {
        return timezoneRepository.findOne(id);
    }

    @Override
    public Timezone saveOrUpdate(Timezone object) {
        return timezoneRepository.save(object);
    }

    @Override
    public void delete(int id) {
        timezoneRepository.delete(id);
    }

    @Override
    public List<Timezone> findByUserId(Integer id) {
        List<Timezone> timezoneList = new ArrayList<>();

        timezoneRepository.findByUserId(id).forEach(timezoneList::add);

        return timezoneList;
    }
}
