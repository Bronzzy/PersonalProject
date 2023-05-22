package com.dhbinh.personalproject.authenticate;
import com.dhbinh.personalproject.security.jwt.JwtRequest;
import com.dhbinh.personalproject.serviceimpl.dto.UserAccountDTO;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/signin")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody JwtRequest loginRequest);

    @PostMapping("/signup")
    ResponseEntity<UserAccountDTO> createUserAccount(@RequestBody UserAccountDTO userAccountDTO);
}
