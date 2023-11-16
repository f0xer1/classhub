package com.classhub.api.model.mapper;

import com.classhub.api.model.Administrator;
import com.classhub.api.model.dto.AdministratorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface AdministratorMapper {
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.role", target = "role")
    AdministratorDto toAdministratorDTO(Administrator administrator);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Administrator updateAdministratorFromDTO(AdministratorDto administratorDto, @MappingTarget Administrator administrator);

}
