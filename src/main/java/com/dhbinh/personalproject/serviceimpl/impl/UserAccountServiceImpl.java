package com.dhbinh.personalproject.serviceimpl.impl;

import com.dhbinh.personalproject.entity.Role;
import com.dhbinh.personalproject.entity.UserAccount;
import com.dhbinh.personalproject.entity.UserRoleAssignment;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.UserAccountMapper;
import com.dhbinh.personalproject.repository.UserAccountRepository;
import com.dhbinh.personalproject.repository.UserRoleAssignmentRepository;
import com.dhbinh.personalproject.security.jwt.JwtUtils;
import com.dhbinh.personalproject.serviceimpl.UserAccountService;
import com.dhbinh.personalproject.serviceimpl.dto.SignupDTO;
import com.dhbinh.personalproject.serviceimpl.dto.UserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.PersistentObjectException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final UserAccountMapper userAccountMapper;

    private final PasswordEncoder encoder;

    private final UserRoleAssignmentRepository userRoleAssignmentRepository;

    private final JwtUtils jwtUtils;

    @Override
    public List<UserAccountDTO> getUserAccounts() {
        return userAccountMapper.toDTOs(userAccountRepository.findAll());
    }

    @Override
    public SignupDTO createUserAccount(UserAccountDTO userAccountDTO) {

        boolean exist = userAccountRepository.existsByUsername(userAccountDTO.getUsername());
        if (exist)
            throw PersonalProjectException.badRequest("UsernameExisted", "Username is already existed");

        boolean validUsername= isUsernameValid(userAccountDTO.getUsername());
        if(!validUsername)
            throw PersonalProjectException.badRequest("InvalidUsername","Username can't contain special character");

        boolean validPassword = isValidPassword(userAccountDTO.getPassword());
        if (!validPassword)
            throw PersonalProjectException.badRequest("InvalidPassword",
                    "Password must at least 12 characters, at least 1 uppercase, 1 number and 1 special character");

        if (userAccountDTO.getUserLastName().isBlank() || userAccountDTO.getUserLastName() == null)
            throw PersonalProjectException.badRequest("LastNameEmpty", "Last name can't be empty or null");

        if (userAccountDTO.getUserFirstName().isBlank() || userAccountDTO.getUserFirstName() == null)
            throw PersonalProjectException.badRequest("FirstNameEmpty", "First name can't be empty or null");

        if (userAccountDTO.getUsername().isBlank() || userAccountDTO.getUsername() == null)
            throw PersonalProjectException.badRequest("UsernameEmpty", "Username can't be empty or null");

        if (userAccountDTO.getPassword().isBlank() || userAccountDTO.getPassword() == null)
            throw PersonalProjectException.badRequest("PasswordEmpty", "Password can't be empty or null");

        UserAccount userAccount = UserAccount.builder()
                .userFirstName(userAccountDTO.getUserFirstName())
                .userLastName(userAccountDTO.getUserLastName())
                .username(userAccountDTO.getUsername())
                .password(encoder.encode(userAccountDTO.getPassword()))
                .active(true)
                .build();
        userAccount = userAccountRepository.save(userAccount);

        UserRoleAssignment assignment = new UserRoleAssignment();
        assignment.setRole(Role.ROLE_USER);
        assignment.setUserAccounts(userAccount);
        userRoleAssignmentRepository.save(assignment);

        SignupDTO signupDTO = SignupDTO.builder()
                .userFirstName(userAccountDTO.getUserFirstName())
                .userLastName(userAccountDTO.getUserLastName())
                .username(userAccountDTO.getUsername())
                .build();

        return signupDTO;
    }

    @Override
    public SignupDTO createAdminAccount(UserAccountDTO userAccountDTO) {

        boolean exist = userAccountRepository.existsByUsername(userAccountDTO.getUsername());
        if (exist)
            throw PersonalProjectException.badRequest("UsernameExisted", "Username is already existed");

        boolean validUsername= isUsernameValid(userAccountDTO.getUsername());
        if(!validUsername)
            throw PersonalProjectException.badRequest("InvalidUsername","Username can't contain special character");

        boolean validPassword = isValidPassword(userAccountDTO.getPassword());
        if (!validPassword)
            throw PersonalProjectException.badRequest("InvalidPassword",
                    "Password must at least 12 characters, at least 1 uppercase, 1 number and 1 special character");

        if (userAccountDTO.getUserLastName().isBlank() || userAccountDTO.getUserLastName() == null)
            throw PersonalProjectException.badRequest("LastNameEmpty", "Last name can't be empty or null");

        if (userAccountDTO.getUserFirstName().isBlank() || userAccountDTO.getUserFirstName() == null)
            throw PersonalProjectException.badRequest("FirstNameEmpty", "First name can't be empty or null");

        if (userAccountDTO.getUsername().isBlank() || userAccountDTO.getUsername() == null)
            throw PersonalProjectException.badRequest("UsernameEmpty", "Username can't be empty or null");

        if (userAccountDTO.getPassword().isBlank() || userAccountDTO.getPassword() == null)
            throw PersonalProjectException.badRequest("PasswordEmpty", "Password can't be empty or null");

        UserAccount userAccount = UserAccount.builder()
                .userFirstName(userAccountDTO.getUserFirstName())
                .userLastName(userAccountDTO.getUserLastName())
                .username(userAccountDTO.getUsername())
                .password(encoder.encode(userAccountDTO.getPassword()))
                .active(true)
                .build();
        userAccount = userAccountRepository.save(userAccount);

        UserRoleAssignment assignment = new UserRoleAssignment();
        assignment.setRole(Role.ROLE_ADMIN);
        assignment.setUserAccounts(userAccount);
        userRoleAssignmentRepository.save(assignment);

        SignupDTO signupDTO = SignupDTO.builder()
                .userFirstName(userAccountDTO.getUserFirstName())
                .userLastName(userAccountDTO.getUserLastName())
                .username(userAccountDTO.getUsername())
                .build();

        return signupDTO;
    }

    public UserAccountDTO getUserFromToken(String token){
        String username = jwtUtils.getUserNameFromJwtToken(token);
        return userAccountMapper.toDTO(userAccountRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username not found")));
    }

    public boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{12,}$";
        return password.matches(regex);
    }

    public boolean isUsernameValid(String input) {
        String regex = "^[a-zA-Z0-9]+$";
        return input.matches(regex);
    }
}
