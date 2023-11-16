package com.classhub.api.service;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.classhub.api.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    Optional<DecodedJWT> signIn(String username, String password);

    User signUpForUser(User user);


    ResponseEntity<Object> getInfoByUsername(String username);
}
