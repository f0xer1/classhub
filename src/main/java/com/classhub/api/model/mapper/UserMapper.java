package com.classhub.api.model.mapper;


import com.classhub.api.model.User;
import com.classhub.api.model.dto.UserCreationDto;
import com.classhub.api.model.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDTO(User user);

    User toUser(UserCreationDto userDto);

}