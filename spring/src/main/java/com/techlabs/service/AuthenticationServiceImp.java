package com.techlabs.service;

import com.techlabs.dto.LoginDTO;
import com.techlabs.dto.LoginResponseDTO;
import com.techlabs.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImp implements AuthenticationService{
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationServiceImp(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public LoginResponseDTO loginUser(LoginDTO loginDTO) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);
            boolean isCustomer = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_STAFF"));
            String userRole = "STAFF";
            if (!isCustomer) userRole = "ADMIN";
            return new LoginResponseDTO(Integer.parseInt(loginDTO.getUsername()), userRole, token);
        } catch (Exception e) {
            throw new BadCredentialsException("wrong username or password");
        }
    }
}
