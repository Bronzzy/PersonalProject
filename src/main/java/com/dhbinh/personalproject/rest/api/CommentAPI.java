package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.CommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/comments")
public interface CommentAPI {

    @PostMapping
    ResponseEntity<CommentDTO> userCreateComment(@RequestHeader("Authorization") String token,
                                             @RequestBody CommentDTO commentDTO);

    @GetMapping
    ResponseEntity<List<CommentDTO>> getAllComments();

    @GetMapping("/username")
    ResponseEntity<List<CommentDTO>> getByUsername(@RequestHeader("Authorization")  String token);

    @DeleteMapping("/delete/{commentID}")
    ResponseEntity<Void> deleteComment(@PathVariable("commentID") Long commentID);
}
