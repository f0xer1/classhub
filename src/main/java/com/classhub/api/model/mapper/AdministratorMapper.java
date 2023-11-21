package com.classhub.api.model.mapper;

import com.classhub.api.model.dto.Admin.AdministratorDto;
import com.classhub.api.model.dto.Admin.AdministratorUpdateDto;
import com.classhub.api.model.users.Administrator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AdministratorMapper {
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.role", target = "role")
    AdministratorDto toAdministratorDTO(Administrator administrator);
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    Administrator toAdministrator(AdministratorUpdateDto updateDto);
}
