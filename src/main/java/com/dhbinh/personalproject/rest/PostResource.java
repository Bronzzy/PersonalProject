package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.PostAPI;
import com.dhbinh.personalproject.serviceimpl.PostService;
import com.dhbinh.personalproject.serviceimpl.dto.PostStatisticDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostResource implements PostAPI {

    private final PostService postService;

    @Override
    public ResponseEntity<List<PostStatisticDTO>> getAllPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @Override
    public ResponseEntity<List<PostStatisticDTO>> getPostByRestaurantName(String restaurant) {
        return ResponseEntity.ok(postService.getPostByRestaurantName(restaurant));
    }

    public ResponseEntity<Void> deleteByPostID(Long postID){
        return ResponseEntity.noContent().build();
    }
}
