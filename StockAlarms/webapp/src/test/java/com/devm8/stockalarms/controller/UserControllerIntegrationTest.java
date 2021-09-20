package com.devm8.stockalarms.controller;

import com.devm8.stockalarms.Application;
import com.devm8.stockalarms.encoder.PasswordEncoder;
import com.devm8.stockalarms.model.UserDO;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    private static final String NAME_ERROR_MESSAGE = "Name can not be empty!";
    private static final String PASSWORD_ERROR_MESSAGE = "Password can not be empty!";
    private static final String EMAIL_ERROR_MESSAGE = "Email is not valid!";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void testCreateUser() throws JSONException {
        UserDO userDO = new UserDO.UserDOBuilder()
                .firstName("admin")
                .lastName("Murza")
                .email("testmail@yahoo.com")
                .password("12345678")
                .build();

        ResponseEntity<String> responseEntity = this.testRestTemplate.postForEntity("http://localhost:" + port + "/users/", userDO, String.class);
        JSONObject responseObject = new JSONObject(responseEntity.getBody());

        assertAll(() -> assertEquals(201, responseEntity.getStatusCodeValue()),
                () -> assertTrue(passwordEncoder.getEncoder().matches("12345678", responseObject.getString("password"))),
                () -> assertEquals("testmail@yahoo.com", responseObject.getString("email")));
    }

    @Test
    public void testCreateUserFailsWhenFirstnameIsEmpty() {
        UserDO userDO = new UserDO.UserDOBuilder()
                .firstName("")
                .lastName("Murza")
                .email("testmail@yahoo.com")
                .password("12345678")
                .build();

        ResponseEntity<String> responseEntity = this.testRestTemplate.postForEntity("http://localhost:" + port + "/users/", userDO, String.class);

        assertAll(() -> assertEquals(400, responseEntity.getStatusCodeValue()),
                () -> assertEquals(NAME_ERROR_MESSAGE, responseEntity.getBody()));
    }

    @Test
    public void testCreateUserFailsWhenPasswordIsEmpty() {
        UserDO userDO = new UserDO.UserDOBuilder()
                .firstName("Cristian")
                .lastName("Murza")
                .email("testmail@yahoo.com")
                .password("")
                .build();

        ResponseEntity<String> responseEntity = this.testRestTemplate.postForEntity("http://localhost:" + port + "/users/", userDO, String.class);

        assertAll(() -> assertEquals(400, responseEntity.getStatusCodeValue()),
                () -> assertEquals(PASSWORD_ERROR_MESSAGE, responseEntity.getBody()));
    }

    @Test
    public void testCreateUserFailsWhenEmailHasIncorrectForm() {
        UserDO userDO = new UserDO.UserDOBuilder()
                .firstName("Cristian")
                .lastName("Murza")
                .email("te#@stmail@yahoo.com")
                .password("12345678")
                .build();

        ResponseEntity<String> responseEntity = this.testRestTemplate.postForEntity("http://localhost:" + port + "/users/", userDO, String.class);

        assertAll(() -> assertEquals(400, responseEntity.getStatusCodeValue()),
                () -> assertEquals(EMAIL_ERROR_MESSAGE, responseEntity.getBody()));
    }
}
