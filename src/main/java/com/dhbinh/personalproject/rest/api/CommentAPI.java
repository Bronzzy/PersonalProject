package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.CommentDTO;
import com.dhbinh.personalproject.service.dto.MonthlyUserCountDTO;
import com.dhbinh.personalproject.service.dto.UserWithMostCommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comments")
public interface CommentAPI {

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping
    ResponseEntity<CommentDTO> userCreateComment(@RequestHeader("Authorization") String token,
                                             @RequestBody CommentDTO commentDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    ResponseEntity<List<CommentDTO>> getAllComments();

    @GetMapping("/username")
    ResponseEntity<List<CommentDTO>> getByUsername(@RequestHeader("Authorization")  String token);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{commentID}")
    ResponseEntity<Void> deleteComment(@PathVariable("commentID") Long commentID);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/topusercomment")
    ResponseEntity<List<UserWithMostCommentDTO>> getUserWithMostComment(@RequestParam("year") int year,
                                                                        @RequestParam("month") int month,
                                                                        @RequestParam("limit") int limit);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/monthlyusercount")
    ResponseEntity<List<MonthlyUserCountDTO>> getMonthlyUserCount();

}
