package com.classhub.api.repository;

import com.classhub.api.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorsRepository extends JpaRepository<Administrator,Long> {
   Optional<Object> findByUsername(String username);
}
