package com.mycodingtest.service;

import com.mycodingtest.dto.JwtResponse;
import com.mycodingtest.dto.SignInRequest;
import com.mycodingtest.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public SignInService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public JwtResponse signIn(SignInRequest request) {
        UsernamePasswordAuthenticationToken authToken = UsernamePasswordAuthenticationToken.unauthenticated(request.username(), request.password());
        Authentication authentication = authenticationManager.authenticate(authToken);

        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Invalid username or password");
        }

        return new JwtResponse(jwtUtil.generateToken(request.username()));
    }
}
