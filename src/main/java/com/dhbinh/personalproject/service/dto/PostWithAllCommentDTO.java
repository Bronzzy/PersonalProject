package com.dhbinh.personalproject.service.dto;

import com.dhbinh.personalproject.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostWithAllCommentDTO {

    private PostDTO postDTO;

    private List<CommentDTO> comments;
}
