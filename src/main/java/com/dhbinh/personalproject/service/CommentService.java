package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.entity.Comment;
import com.dhbinh.personalproject.entity.Post;
import com.dhbinh.personalproject.service.dto.CommentDTO;
import com.dhbinh.personalproject.service.dto.MonthlyUserCountDTO;
import com.dhbinh.personalproject.service.dto.UserWithMostCommentDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    CommentDTO userCreateComment(String token, CommentDTO commentDTO);

    List<CommentDTO> getAllComment();

    List<CommentDTO> getByUsername(String token);

    void deleteComment(Long commentID);

    List<UserWithMostCommentDTO> getUserWithMostComment(int year, int month, Pageable pageable);

    List<MonthlyUserCountDTO> getMonthlyUserCount();

    List<Comment> findByPost(Post post);
}
