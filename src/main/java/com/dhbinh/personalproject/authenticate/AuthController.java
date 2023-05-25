package com.dhbinh.personalproject.authenticate;
import com.dhbinh.personalproject.security.jwt.JwtRequest;

import com.dhbinh.personalproject.service.dto.SignupDTO;
import com.dhbinh.personalproject.service.dto.UserAccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/signin")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody JwtRequest loginRequest);

    @PostMapping("/usersignup")
    ResponseEntity<SignupDTO> createUserAccount(@RequestBody UserAccountDTO userAccountDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adminsignup")
    ResponseEntity<SignupDTO> createAdminAccount(@RequestBody UserAccountDTO userAccountDTO);
}
