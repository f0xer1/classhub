package com.classhub.api.service;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.classhub.api.model.user.User;

import java.util.Optional;
import java.util.function.Consumer;

public interface UserService {
    Optional<DecodedJWT> signIn(String username, String password);

    User signUpForUser(User user);

}
