package com.harsha.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsha.app.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RefreshScope
public class CheckProperties {
//    @Value("${db.username}")
//    private String username;
//
//    @Value("${db.password}")
//    private String password;

    @Autowired
    private DatabaseConfig databaseConfig;

    @GetMapping(value = "/get-credentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCredentials() {
        Map<String, String> credentialMap = new HashMap<>();
        credentialMap.put("username", databaseConfig.getUsername());
        credentialMap.put("password", databaseConfig.getPassword());
//        credentialMap.put("credentials", username + " " + password);

        ObjectMapper objectMapper = new ObjectMapper();
        String credentials = null;
        try {
            credentials = objectMapper.writeValueAsString(credentialMap);
        } catch (JsonProcessingException e) {}
        return new ResponseEntity<>(credentials, HttpStatus.OK);
    }

}
