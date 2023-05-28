package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.UserAccountAPI;
import com.dhbinh.personalproject.service.impl.UserAccountServiceImpl;
import com.dhbinh.personalproject.service.dto.SignupDTO;
import com.dhbinh.personalproject.service.dto.UserAccountDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserAccountResource implements UserAccountAPI {

    private final UserAccountServiceImpl userAccountService;

//    @Override
//    public ResponseEntity<SignupDTO> createUserAccount(UserAccountDTO userAccountDTO) {
//        SignupDTO signupDTO = userAccountService.createUserAccount(userAccountDTO);
//        return ResponseEntity.created(URI.create("/useraccounts/" + signupDTO.getID())).body(signupDTO);
//    }

    @Override
    public ResponseEntity<UserAccountDTO> getUserFromToken(String token) {
        return ResponseEntity.ok(userAccountService.getUserFromToken(token));
    }
}
