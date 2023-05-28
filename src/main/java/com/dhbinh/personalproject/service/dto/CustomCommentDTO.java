package com.dhbinh.personalproject.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomCommentDTO {

    private String content;

    private LocalDate createDate;

    private String username;
}
