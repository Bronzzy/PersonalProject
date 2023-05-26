package com.dhbinh.personalproject.service.dto;

import com.dhbinh.personalproject.entity.Post;
import com.dhbinh.personalproject.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long postID;

    private Long commentID;

    private String content;

    private LocalDate createDate;

    private String username;

}
