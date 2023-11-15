package com.classhub.api.model.mapper;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.classhub.api.controller.auth.JWTToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JWTTokenMapper {
    @Mapping(target = "expiresAt", expression = "java(jwt.getExpiresAt().getTime() / 1000)")
    JWTToken toPayload(DecodedJWT jwt);
}
