package com.classhub.api.model.user;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDTO(User user);

    User toUser(UserCreationDto userDto);

}