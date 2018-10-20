package com.example.restservice.repository;

import com.example.restservice.entity.PersonEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<PersonEntity, Long> {
}
