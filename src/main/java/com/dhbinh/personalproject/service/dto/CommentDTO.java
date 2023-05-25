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

    private Long postCommentID;
    private String comment;
    private LocalDate commentDate;
    private UserAccount userAccount;
    private Post post;

}
