package com.example.restservice.service;

import com.example.restservice.entity.PersonEntity;
import com.example.restservice.model.RestResult;

public interface CrudService {

    RestResult create(PersonEntity person);

    RestResult read(Long id);

    RestResult update(PersonEntity person);

    RestResult delete(PersonEntity person);
    RestResult delete(Long id);
}
