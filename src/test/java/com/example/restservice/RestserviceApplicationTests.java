package com.example.restservice;

import com.example.restservice.entity.PersonEntity;
import com.example.restservice.model.RestResult;
import com.example.restservice.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestserviceApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    private RestTemplate restTemplate = new RestTemplate();

    private final String URL = "http://localhost:8080/PersonService/";

    private PersonEntity testUser = new PersonEntity(7674350L, "test user");
    private PersonEntity updateUser = new PersonEntity(7674350L, "updated test user");

    @Test
    public void createPersonTest() {
        RestResult restResult = restTemplate.postForObject(URL + "create", testUser, RestResult.class);

        assertNotNull(restResult);
        assertTrue(restResult.getSuccess());

        personRepository.deleteById(testUser.getId());
    }

    @Test
    public void constraintTest() {
        PersonEntity constraintUser = new PersonEntity(34759L, "tu");

        RestResult restResult = restTemplate.postForObject(URL + "create", constraintUser, RestResult.class);

        assertNotNull(restResult);
        assertFalse(restResult.getSuccess());
        System.out.println(restResult.getSuccessMessage());

        constraintUser = new PersonEntity(34759L, "");

        restResult = restTemplate.postForObject(URL + "create", constraintUser, RestResult.class);

        assertNotNull(restResult);
        assertFalse(restResult.getSuccess());
        System.out.println(restResult.getSuccessMessage());
    }

    @Test
    public void readPersonTest() {
        personRepository.save(testUser);

        RestResult restResult = restTemplate.getForObject(URL + "read?id=" + testUser.getId(), RestResult.class);

        assertNotNull(restResult);
        assertTrue(restResult.getSuccess());
        assertEquals(restResult.getPerson(), testUser);

        personRepository.delete(testUser);
    }

    @Test
    public void updatePersonTest() {
        personRepository.save(testUser);

        RestResult restResult = restTemplate.postForObject(URL + "update", updateUser, RestResult.class);

        assertNotNull(restResult);
        assertTrue(restResult.getSuccess());
        assertEquals(restResult.getPerson().getName(), updateUser.getName());

        personRepository.delete(testUser);

    }

    @Test
    public void deletePersonTest() {
        personRepository.save(testUser);

        RestResult restResult = restTemplate.getForObject(URL + "delete?id=" + testUser.getId(), RestResult.class);
        assertNotNull(restResult);
        assertTrue(restResult.getSuccess());

        Optional<PersonEntity> byId = personRepository.findById(testUser.getId());
        assertFalse(byId.isPresent());

        personRepository.save(testUser);
        restResult = restTemplate.postForObject(URL + "delete", testUser, RestResult.class);
        assertNotNull(restResult);
        assertTrue(restResult.getSuccess());

        byId = personRepository.findById(testUser.getId());
        assertFalse(byId.isPresent());
    }
}
