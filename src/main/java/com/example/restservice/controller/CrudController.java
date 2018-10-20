package com.example.restservice.controller;

import com.example.restservice.entity.PersonEntity;
import com.example.restservice.model.RestResult;
import com.example.restservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CrudController {

    private final CrudService crudService;

    @Autowired
    public CrudController(CrudService crudService) {
        this.crudService = crudService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RestResult create(@RequestBody PersonEntity person) {
        return crudService.create(person);
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public RestResult read(@RequestParam("id") Long id) {
        return crudService.read(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RestResult update(@RequestBody PersonEntity person) {
        return crudService.update(person);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public RestResult delete(@RequestParam(value = "id") Long id) {
        return crudService.delete(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RestResult delete(@RequestBody PersonEntity person) {
        return crudService.delete(person);
    }

}
