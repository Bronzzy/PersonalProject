package com.dhbinh.personalproject.serviceimpl.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {
    @JsonIgnore
    private Long userAccountID;

    private String userFirstName;

    private String userLastName;

    private String username;
}
