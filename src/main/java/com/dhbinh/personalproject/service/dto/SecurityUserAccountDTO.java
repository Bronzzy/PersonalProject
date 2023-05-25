package com.dhbinh.personalproject.serviceimpl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityUserAccountDTO {
    private String principalName;
    private String username;
}
