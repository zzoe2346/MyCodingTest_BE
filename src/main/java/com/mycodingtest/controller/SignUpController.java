package com.mycodingtest.controller;

import com.mycodingtest.dto.JwtResponse;
import com.mycodingtest.dto.SignUpRequest;
import com.mycodingtest.service.SignUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<JwtResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(signUpService.signUp(request));
    }
}
