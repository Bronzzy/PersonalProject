package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.CommentAPI;
import com.dhbinh.personalproject.service.CommentService;
import com.dhbinh.personalproject.service.dto.CustomCommentStatisticDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentResource implements CommentAPI {

    private final CommentService commentService;
    @Override
    public ResponseEntity<List<CustomCommentStatisticDTO>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComment());
    }

    @Override
    public ResponseEntity<List<CustomCommentStatisticDTO>> getByUsername(String token) {
        return ResponseEntity.ok(commentService.getByUsername(token));
    }
}
