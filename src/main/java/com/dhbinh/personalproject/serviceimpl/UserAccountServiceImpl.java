package com.dhbinh.personalproject.serviceimpl;

import com.dhbinh.personalproject.entity.Role;
import com.dhbinh.personalproject.entity.UserAccount;
import com.dhbinh.personalproject.entity.UserRoleAssignment;
import com.dhbinh.personalproject.mapper.UserAccountMapper;
import com.dhbinh.personalproject.repository.UserAccountRepository;
import com.dhbinh.personalproject.repository.UserRoleAssignmentRepository;
import com.dhbinh.personalproject.serviceimpl.dto.UserAccountDTO;
import lombok.RequiredArgsConstructor;
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

    @Override
    public List<UserAccountDTO> getUserAccounts() {
        return userAccountMapper.toDTOs(userAccountRepository.findAll());
    }

    @Override
    public UserAccountDTO createAccount(UserAccountDTO userAccountDTO) {
//        CustomerDto customerDto = customerService.getCustomerById(customerId);
//        Customer customer = CustomerMapper.INSTANCE.toEntity(customerDto);

        //Throw exceptions
//        if (accountDto.getTotalBalance() < 0) {
//            throw ProjectException.badRequest("Balance Cannot Negative", "Enter again");
//        }
//        if (!isAlphanumeric(accountDto.getUserName())) {
//            throw ProjectException.badRequest("WrongUserFormat", "User should only contain alphabet and number");
//        }
//        if (!isAlphanumericWithSpecial(accountDto.getUserPassword())) {
//            throw ProjectException.badRequest("WrongUserPasswordFormat", "Password should only contain alphabet,number, special character and minimum 6 characters");
//        }
//        if (accountRepository.existsByUserName(accountDto.getUserName())) {
//            throw  ProjectException.badRequest("UserNameExisted", "User name is already taken");
//        }
//        if (accountRepository.existsByEmail(accountDto.getEmail())) {
//            throw  ProjectException.badRequest("EmailExisted", "Email has been used, try different email");
//        }
        UserAccount userAccount = UserAccount.builder()
                .userFirstName(userAccountDTO.getUserFirstName())
                .userLastName(userAccountDTO.getUserLastName())
                .username(userAccountDTO.getUsername())
                .password(encoder.encode(userAccountDTO.getPassword()))
                .build();
        userAccount = userAccountRepository.save(userAccount);

        UserRoleAssignment assignment = new UserRoleAssignment();
        assignment.setRole(Role.ROLE_USER);
        assignment.setUserAccounts(userAccount);
        userRoleAssignmentRepository.save(assignment);


        return userAccountMapper.toDTO(userAccount);
    }
}
