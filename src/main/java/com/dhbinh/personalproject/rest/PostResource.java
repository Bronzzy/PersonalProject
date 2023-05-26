package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.PostAPI;
import com.dhbinh.personalproject.service.PostService;
import com.dhbinh.personalproject.service.dto.PostDTO;
import com.dhbinh.personalproject.service.dto.RestaurantDTO;
import com.dhbinh.personalproject.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostResource implements PostAPI {

    private final PostService postService;


    @Override
    public ResponseEntity<PostDTO> createPost(PostDTO postDTO) {
        log.info("Request to create post {}",postDTO);
        PostDTO dto = postService.createPost(postDTO);
        return ResponseEntity.created(URI.create("/api/posts" + dto.getID())).body(dto);
    }

    @Override
    public ResponseEntity<List<PostDTO>> getAllPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @Override
    public ResponseEntity<PostDTO> getByPostID(Long postID) {
        return ResponseEntity.ok(postService.getPostByID(postID));
    }

    @Override
    public ResponseEntity<PostDTO> updatePost(Long postID, PostDTO postDTO) {
        PostDTO dto = postService.updatePost(postID, postDTO);
        return ResponseEntity.created(URI.create("/api/posts" + dto.getID())).body(dto);
    }

    public ResponseEntity<Void> deleteByPostID(Long postID){
        postService.deleteByPostID(postID);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<PostDTO>> getPostByRestaurantName(String restaurant) {
        return ResponseEntity.ok(postService.getPostByRestaurantName(restaurant));
    }
}
