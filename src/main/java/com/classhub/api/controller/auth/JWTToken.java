package com.classhub.api.controller.auth;

import lombok.Data;

@Data
public class JWTToken {
    private String token;
    private String type;
    private String algorithm;
    private Long expiresAt;
}
