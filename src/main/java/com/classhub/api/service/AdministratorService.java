package com.classhub.api.service;

import com.classhub.api.model.users.Administrator;
import com.classhub.api.model.dto.AdministratorDto;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface AdministratorService {
    ResponseEntity<String> createAdmin(Long id) ;

    Optional<Administrator> getAdminById(Long id);
    Administrator getAdminByUsername(String username);

    ResponseEntity<String> editAdmin(AdministratorDto administratorDto);
}
