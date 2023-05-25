package com.dhbinh.personalproject.service;


import com.dhbinh.personalproject.service.dto.SignupDTO;
import com.dhbinh.personalproject.service.dto.UserAccountDTO;

import java.util.List;

public interface UserAccountService {
    List<UserAccountDTO> getUserAccounts();

    SignupDTO createUserAccount(UserAccountDTO userAccountDTO);

    SignupDTO createAdminAccount(UserAccountDTO userAccountDTO);

}
