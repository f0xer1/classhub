package com.classhub.api.service;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.classhub.api.model.users.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    Optional<DecodedJWT> signIn(String username, String password);




    ResponseEntity<Object> getInfoByUsername(String username);

    User signUpForAdmin(User user);

    User signUpForStudent(User user);

    User signUpForTeacher(User user);

    Optional<User> findById(Long id);
}
