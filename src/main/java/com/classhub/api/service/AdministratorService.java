package com.classhub.api.service;

import com.classhub.api.model.users.Administrator;

import java.util.Optional;

public interface AdministratorService {
    void createAdmin(Long id) ;

    Optional<Administrator> getAdminById(Long id);
    Administrator getAdminByUsername(String username);

   Administrator editAdmin(Administrator administrator, String username);

}
