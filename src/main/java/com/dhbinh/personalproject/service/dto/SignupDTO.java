package com.dhbinh.personalproject.service.dto;

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
    private Long ID;

    private String firstName;

    private String lastName;

    private String username;
}
