package com.classhub.api.config;

import com.classhub.api.exeption.UserNotFoundException;
import com.classhub.api.repository.AdministratorsRepository;
import com.classhub.api.repository.StudentsRepository;
import com.classhub.api.repository.TeachersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final StudentsRepository studentsRepository;
    private final AdministratorsRepository administratorsRepository;
    private final TeachersRepository teachersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        var user =  studentsRepository.findByUsername(username)
                .or(() -> administratorsRepository.findByUsername(username))
                .or(() -> teachersRepository.findByUsername(username))
                .orElseThrow(() -> new UserNotFoundException("User with username %s not found".formatted(username)));

        return new UserDetailsImpl<>(user);
    }
}
