package com.classhub.api.repository;

import com.classhub.api.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdministratorsRepository extends JpaRepository<Administrator,Long> {

}
