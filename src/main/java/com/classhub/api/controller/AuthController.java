package com.classhub.api.controller;

import com.classhub.api.controller.auth.JWTToken;
import com.classhub.api.model.mapper.JWTTokenMapper;
import com.classhub.api.model.dto.UserCreationDto;
import com.classhub.api.model.dto.UserDto;
import com.classhub.api.model.mapper.UserMapper;
import com.classhub.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final JWTTokenMapper jwtTokenMapper;
    @PostMapping("/admin-sign-up")
    public ResponseEntity<UserDto> signUpForAdmin(@RequestBody @Valid UserCreationDto userDto) {
        var newUser = userMapper.toUser(userDto);
        newUser.setRole("admin");
        userService.signUpForUser(newUser);
        return new ResponseEntity<>(userMapper.toUserDTO(newUser), HttpStatus.CREATED);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<JWTToken> signIn(@RequestBody @Valid UserCreationDto userDto) {
        return ResponseEntity.of(userService
                .signIn(userDto.getUsername(), userDto.getPwd())
               .map(jwtTokenMapper::toPayload));
   }

    @PostMapping("/admin-sign-user")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserCreationDto userDto) {
        var newUser = userService.signUpForUser(userMapper.toUser(userDto));
        return new ResponseEntity<>(userMapper.toUserDTO(newUser), HttpStatus.CREATED);
    }

}
