package com.mycodingtest.service;

import com.mycodingtest.dto.JwtResponse;
import com.mycodingtest.dto.SignUpRequest;
import com.mycodingtest.entity.User;
import com.mycodingtest.repository.UserRepository;
import com.mycodingtest.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public SignUpService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public JwtResponse signUp(SignUpRequest request) {
        boolean isUserExist = userRepository.existsByUsername(request.username());

        if (isUserExist) {
            throw new RuntimeException("Username already exists");
        }

        userRepository.save(new User(request.username(), bCryptPasswordEncoder.encode(request.password()), "email", "ROLE_USER"));

        return new JwtResponse(jwtUtil.generateToken(request.username()));
    }

}
