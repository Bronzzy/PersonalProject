package com.dhbinh.personalproject.serviceimpl.dto;

import com.dhbinh.personalproject.entity.UserRoleAssignment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {
    private Long userAccountID;

    private String userFirstName;

    private String userLastName;

    private String username;

    private String password;

    private List<UserRoleAssignment> roles = new ArrayList<>();
}
