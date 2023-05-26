package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO userCreateComment(String token, CommentDTO commentDTO);

    List<CommentDTO> getAllComment();

    List<CommentDTO> getByUsername(String token);

    void deleteComment(Long commentID);
}
