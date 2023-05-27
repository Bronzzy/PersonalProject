package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.CommentAPI;
import com.dhbinh.personalproject.service.CommentService;
import com.dhbinh.personalproject.service.dto.CommentDTO;
import com.dhbinh.personalproject.service.dto.MonthlyUserCountDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentResource implements CommentAPI {

    private final CommentService commentService;

    @Override
    public ResponseEntity<CommentDTO> userCreateComment(String token, CommentDTO commentDTO) {
        CommentDTO dto = commentService.userCreateComment(token, commentDTO);
        return ResponseEntity.created(URI.create("/api/comments/" + dto.getCommentID())).body(dto);
    }

    @Override
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComment());
    }

    @Override
    public ResponseEntity<List<CommentDTO>> getByUsername(String token) {
        return ResponseEntity.ok(commentService.getByUsername(token));
    }

    @Override
    public ResponseEntity<Void> deleteComment(Long commentID) {
        commentService.deleteComment(commentID);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<Object[]>> getTopUserWithMostComment(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return ResponseEntity.ok(commentService.getTopUserWithMostComment(pageable));
    }

    @Override
    public ResponseEntity<List<MonthlyUserCountDTO>> getMonthlyUserCount() {
        return ResponseEntity.ok(commentService.getMonthlyUserCount());
    }


}
