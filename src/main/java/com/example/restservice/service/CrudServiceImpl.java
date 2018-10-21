package com.example.restservice.service;

import com.example.restservice.profiling.LogExecutionTime;
import com.example.restservice.entity.PersonEntity;
import com.example.restservice.model.RestResult;
import com.example.restservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrudServiceImpl implements CrudService {

    private final PersonRepository personRepository;

    @Autowired
    public CrudServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @LogExecutionTime
    public RestResult create(PersonEntity person) {
        PersonEntity returnPerson = personRepository.save(person);
        return RestResult.builder()
                .success(true)
                .successMessage("created person!")
                .person(returnPerson)
                .build();
    }

    @Override
    @LogExecutionTime
    public RestResult read(Long id) {
        Optional<PersonEntity> person = personRepository.findById(id);
        if (person.isPresent()) {
            return RestResult.builder()
                    .success(true)
                    .successMessage("found person!")
                    .person(person.get())
                    .build();
        } else {
            return RestResult.builder()
                    .success(false)
                    .successMessage("did not find person!")
                    .build();
        }
    }

    @Override
    @LogExecutionTime
    public RestResult update(PersonEntity person) {
        PersonEntity savedPerson = personRepository.save(person);
        return RestResult.builder()
                .success(true)
                .successMessage("updated person!")
                .person(savedPerson)
                .build();
    }

    @Override
    @LogExecutionTime
    public RestResult delete(PersonEntity person) {
        personRepository.delete(person);
        return RestResult.builder()
                .success(true)
                .successMessage("deleted person!")
                .person(person)
                .build();
    }

    @Override
    @LogExecutionTime
    public RestResult delete(Long id) {
        personRepository.deleteById(id);
        return RestResult.builder()
                .success(true)
                .successMessage("deleted person!")
                .build();
    }
}
