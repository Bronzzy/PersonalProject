package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.entity.Comment;
import com.dhbinh.personalproject.service.dto.CustomCommentStatisticDTO;
import com.dhbinh.personalproject.service.dto.CustomPostStatisticDTO;

import java.util.List;

public interface CommentService {

    List<CustomCommentStatisticDTO> getAllComment();

    List<CustomCommentStatisticDTO> getByUsername(String token);
}
