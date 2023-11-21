package com.classhub.api.model.mapper;


import com.classhub.api.model.users.User;
import com.classhub.api.model.dto.User.UserCreationDto;
import com.classhub.api.model.dto.User.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDTO(User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toUser(UserCreationDto userDto);

}