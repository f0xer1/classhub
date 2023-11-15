package com.classhub.api.controller;

import com.classhub.api.model.user.UserCreationDto;
import com.classhub.api.model.user.UserDto;
import com.classhub.api.model.user.UserMapper;
import com.classhub.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUpForAdmin(@RequestBody @Valid UserCreationDto userDto) {
        var newUser = userService.signUpForAdmin(userMapper.toUser(userDto));
        return new ResponseEntity<>(userMapper.toUserDTO(newUser), HttpStatus.CREATED);
    }
//    public ResponseEntity<JwtToken> signIn(@RequestBody @Valid Credentials credentials) {
//        return ResponseEntity.of(userService
//                .signIn(credentials.getUsername(), credentials.getPassword())
//                .map(jwtTokenMapper::toPayload));
//    }
}
