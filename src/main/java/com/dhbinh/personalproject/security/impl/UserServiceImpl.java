package com.dhbinh.personalproject.security.impl;

import com.dhbinh.personalproject.entity.UserAccount;
import com.dhbinh.personalproject.mapper.UserAccountMapper;
import com.dhbinh.personalproject.repository.UserAccountRepository;
import com.dhbinh.personalproject.serviceimpl.UserAccountServiceImpl;
import com.dhbinh.personalproject.serviceimpl.dto.UserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserAccountServiceImpl {

    private final UserAccountRepository userAccountRepository;

    private final UserAccountMapper userAccountMapper;
    @Override
    public List<UserAccountDTO> getUserAccounts() {
        return userAccountMapper.toDTOs(userAccountRepository.findAll());
    }
}
