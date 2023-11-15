package com.classhub.api.service.impl;

import com.classhub.api.config.JWTTokenProvider;
import com.classhub.api.exeption.UserAlreadyExistsException;
import com.classhub.api.model.user.User;
import com.classhub.api.repository.UserRepository;
import com.classhub.api.service.AdministratorService;
import com.classhub.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JWTTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AdministratorService administratorService;

    @Override
    public User signUpForAdmin(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(
                    "Username %s is already in use".formatted(user.getUsername()));
        }
        user.setRole("ROLE_ADMINISTRATOR");
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        userRepository.save(user);
        administratorService.createAdmin(user.getId());
        return user;
    }
}
