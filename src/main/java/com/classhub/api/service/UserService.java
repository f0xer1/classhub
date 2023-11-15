package com.classhub.api.service;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.classhub.api.model.user.User;

import java.util.Optional;

public interface UserService {
    User signUpForAdmin(User user);
    Optional<DecodedJWT> signIn(String username, String password);
}
