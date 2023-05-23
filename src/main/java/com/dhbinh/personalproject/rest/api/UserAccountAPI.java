package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.serviceimpl.dto.SignupDTO;
import com.dhbinh.personalproject.serviceimpl.dto.UserAccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/useraccounts")
public interface UserAccountAPI {


    @PostMapping
    ResponseEntity<SignupDTO> createUserAccount(@RequestBody UserAccountDTO userAccountDTO);

    @GetMapping
    ResponseEntity<UserAccountDTO> getUserFromToken(@RequestHeader("Authorization") String token);
}
