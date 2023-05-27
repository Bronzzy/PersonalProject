package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.CommentDTO;
import com.dhbinh.personalproject.service.dto.MonthlyUserCountDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    CommentDTO userCreateComment(String token, CommentDTO commentDTO);

    List<CommentDTO> getAllComment();

    List<CommentDTO> getByUsername(String token);

    void deleteComment(Long commentID);

    List<Object[]> getTopUserWithMostComment(Pageable pageable);

    List<MonthlyUserCountDTO> getMonthlyUserCount();
}
