package com.classhub.api.service;

import org.springframework.http.ResponseEntity;

public interface AdministratorService {
    ResponseEntity<String> createAdmin(Long id) ;
}
