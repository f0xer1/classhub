package com.classhub.api.model.dto;

import lombok.Data;

@Data
public class AdministratorDto {

    private String username;
    private String role;
    private String first_name;
    private String last_name;
    private String patronymic;
}
