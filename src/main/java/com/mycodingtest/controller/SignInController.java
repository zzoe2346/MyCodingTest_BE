package com.mycodingtest.controller;

import com.mycodingtest.dto.JwtResponse;
import com.mycodingtest.dto.SignInRequest;
import com.mycodingtest.service.SignInService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

    private final SignInService signInService;

    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(signInService.signIn(request));
    }
}
