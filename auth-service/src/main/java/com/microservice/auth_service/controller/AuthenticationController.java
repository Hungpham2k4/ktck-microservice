package com.microservice.auth_service.controller;

import com.microservice.auth_service.dto.request.LoginRequestDTO;
import com.microservice.auth_service.dto.request.RegisterRequestDTO;
import com.microservice.auth_service.dto.response.AuthenticationResponseDTO;
import com.microservice.auth_service.dto.response.RegisterResponseDTO;
import com.microservice.auth_service.exception.CustomException;
import com.microservice.auth_service.user.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
@Validated
@CrossOrigin(origins = "*")
public class AuthenticationController {
    public final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) throws Exception {
        RegisterResponseDTO registerResponseDTO = authenticationService.register(registerRequestDTO);
        return ResponseEntity.status(registerResponseDTO.getStatus()).body(registerResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws Exception {
        log.info("Password: {}", loginRequestDTO.getPassword());
        log.info("Username: {}", loginRequestDTO.getUsername());

        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.login(loginRequestDTO);
        return ResponseEntity.status(authenticationResponseDTO.getStatus()).body(authenticationResponseDTO);
    }

    @PostMapping("/refresh-token")
    public AuthenticationResponseDTO refreshToken(HttpServletRequest req, HttpServletResponse res) throws CustomException {
        log.info("Request: {}", req);
        return authenticationService.refreshToken(req, res);
    }
}