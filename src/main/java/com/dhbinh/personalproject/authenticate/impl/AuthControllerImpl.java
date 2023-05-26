package com.dhbinh.personalproject.authenticate.impl;

import com.dhbinh.personalproject.authenticate.AuthController;
import com.dhbinh.personalproject.service.impl.UserAccountServiceImpl;
import com.dhbinh.personalproject.service.dto.SignupDTO;
import com.dhbinh.personalproject.service.dto.UserAccountDTO;
import com.dhbinh.personalproject.service.impl.UserDetailsImpl;
import com.dhbinh.personalproject.security.jwt.JwtRequest;
import com.dhbinh.personalproject.security.jwt.JwtResponse;
import com.dhbinh.personalproject.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserAccountServiceImpl userAccountService;

    private final JwtUtils jwtUtils;

    public ResponseEntity<?> authenticateUser(JwtRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                roles));
    }

    @Override
    public ResponseEntity<SignupDTO> createUserAccount(UserAccountDTO userAccountDTO) {
        SignupDTO userAccount = userAccountService.createUserAccount(userAccountDTO);
        return ResponseEntity.created(URI.create("/useraccounts/" + userAccount.getID())).body(userAccount);
    }

    @Override
    public ResponseEntity<SignupDTO> createAdminAccount(UserAccountDTO userAccountDTO) {
        SignupDTO userAccount = userAccountService.createAdminAccount(userAccountDTO);
        return ResponseEntity.created(URI.create("/useraccounts/" + userAccount.getID())).body(userAccount);
    }

}
