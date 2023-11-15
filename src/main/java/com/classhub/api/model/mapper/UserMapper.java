package com.classhub.api.model.mapper;


import com.classhub.api.model.user.User;
import com.classhub.api.model.user.UserCreationDto;
import com.classhub.api.model.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDTO(User user);

    User toUser(UserCreationDto userDto);

}