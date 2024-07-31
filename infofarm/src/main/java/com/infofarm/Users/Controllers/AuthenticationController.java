package com.infofarm.Users.Controllers;

import com.infofarm.Users.Dto.Request.AuthCreateUserDTO;
import com.infofarm.Users.Dto.Request.AuthLoginRequest;
import com.infofarm.Users.Dto.Response.AuthResponse;
import com.infofarm.Users.Implementation.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    UserDetailServiceImpl userDetailService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserDTO authCreateUser) {
        return new ResponseEntity<>(userDetailService.createUser(authCreateUser), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        return  new ResponseEntity<>(userDetailService.loginUser(userRequest), HttpStatus.OK);
    }
}
