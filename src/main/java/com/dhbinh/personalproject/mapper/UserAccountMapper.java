package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.UserAccount;
import com.dhbinh.personalproject.serviceimpl.dto.UserAccountDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccountDTO toDTO(UserAccount userAccount);
    List<UserAccountDTO> toDTOs(List<UserAccount> userAccounts);

}
