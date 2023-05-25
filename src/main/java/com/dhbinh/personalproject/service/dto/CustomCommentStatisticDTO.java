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
public class CustomCommentStatisticDTO {
    private String comment;
    private LocalDate commentDate;
    private String userName;
    private Long postID;
}
