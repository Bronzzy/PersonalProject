package com.dhbinh.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithMostCommentDTO {

    private Integer year;
    private Integer month;
    private String username;
    private Long numberOfComment;
}
