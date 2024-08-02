package com.infofarm.Users.Controllers;

import com.infofarm.Users.Dto.Request.Auth.AuthCreateUserDTO;
import com.infofarm.Users.Dto.Request.Auth.AuthLoginRequest;
import com.infofarm.Users.Dto.Response.Auth.AuthResponse;
import com.infofarm.Users.Implementation.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    UserDetailServiceImpl userDetailService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestPart @Valid AuthCreateUserDTO authCreateUser,
                                                 @RequestPart(value = "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(userDetailService.createUser(authCreateUser, file), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        return  new ResponseEntity<>(userDetailService.loginUser(userRequest), HttpStatus.OK);
    }
}
