package com.dhbinh.personalproject.serviceimpl;


import com.dhbinh.personalproject.serviceimpl.dto.SignupDTO;
import com.dhbinh.personalproject.serviceimpl.dto.UserAccountDTO;

import java.util.List;

public interface UserAccountService {
    List<UserAccountDTO> getUserAccounts();

    SignupDTO createUserAccount(UserAccountDTO userAccountDTO);

    SignupDTO createAdminAccount(UserAccountDTO userAccountDTO);

}
