package com.example.restservice.model;

import com.example.restservice.entity.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestResult {

    private Boolean success;
    private String successMessage;
    private PersonEntity person;
}
