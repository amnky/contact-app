package com.techlabs.controller;

import com.techlabs.dto.LoginDTO;
import com.techlabs.dto.LoginResponseDTO;
import com.techlabs.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginDTO loginDTO){
        LoginResponseDTO loginResponseDTO=authenticationService.loginUser(loginDTO);
        return  new ResponseEntity<>(loginResponseDTO,HttpStatus.OK);
    }

}
