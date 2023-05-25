package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Comment;
import com.dhbinh.personalproject.repository.CommentRepository;
import com.dhbinh.personalproject.security.jwt.JwtUtils;
import com.dhbinh.personalproject.service.CommentService;
import com.dhbinh.personalproject.service.dto.CustomCommentStatisticDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final JwtUtils jwtUtils;

    @Override
    public List<CustomCommentStatisticDTO> getAllComment() {

        List<CustomCommentStatisticDTO> resultList = new ArrayList<>();

        List<Comment> commentList = commentRepository.findAll();

        for (Comment comment : commentList) {
            CustomCommentStatisticDTO dto = CustomCommentStatisticDTO.builder()
                    .comment(comment.getComment())
                    .commentDate(comment.getCommentDate())
                    .userName(comment.getUserAccount().getUsername())
                    .postID(comment.getPost().getPostID())
                    .build();
            resultList.add(dto);
        }
        return resultList;
    }

    @Override
    public List<CustomCommentStatisticDTO> getByUsername(String token) {

        List<CustomCommentStatisticDTO> resultList = new ArrayList<>();

        String username = jwtUtils.getUserNameFromJwtToken(token);
        log.info("username: {}", username);

        List<Comment> comments = commentRepository.getByUsername(username);
        for (Comment comment : comments) {
            CustomCommentStatisticDTO dto = CustomCommentStatisticDTO.builder()
                    .comment(comment.getComment())
                    .commentDate(comment.getCommentDate())
                    .userName(comment.getUserAccount().getUsername())
                    .postID(comment.getPost().getPostID())
                    .build();
            resultList.add(dto);
        }

        return resultList;
    }
}
