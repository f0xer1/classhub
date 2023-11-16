package com.classhub.api.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String role;
}
