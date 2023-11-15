package com.classhub.api.service.impl;

import com.classhub.api.model.Administrator;
import com.classhub.api.repository.AdministratorsRepository;
import com.classhub.api.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {
    private  final AdministratorsRepository administratorsRepository;
    @Override
    public ResponseEntity<String> createAdmin(Long id) {
        try {
        administratorsRepository.save(new Administrator(id));
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
}
