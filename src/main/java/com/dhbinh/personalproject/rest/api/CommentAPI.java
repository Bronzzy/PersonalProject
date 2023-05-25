package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.CustomCommentStatisticDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/auth/comments")
public interface CommentAPI {

    @GetMapping
    ResponseEntity<List<CustomCommentStatisticDTO>> getAllComments();

    @GetMapping("/username")
    ResponseEntity<List<CustomCommentStatisticDTO>> getByUsername(@RequestHeader("Authorization")  String token);


}
