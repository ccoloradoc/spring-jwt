package com.colorado.jwt.services;

import java.util.List;

/**
 * Created by colorado on 28/03/17.
 */
public interface CrudService<T> {
    List<T> findAll();
    T findById(int id);
    T saveOrUpdate(T object);
    void delete(int id);
}