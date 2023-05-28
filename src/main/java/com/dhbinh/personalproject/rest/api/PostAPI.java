package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.PostDTO;
import com.dhbinh.personalproject.service.dto.PostWithAllCommentDTO;
import com.dhbinh.personalproject.service.dto.RestaurantDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/posts")
public interface PostAPI {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO);

    @GetMapping
    ResponseEntity<List<PostDTO>> getAllPost();

    @GetMapping("/bypostid")
    ResponseEntity<PostDTO> getByPostID(@RequestParam("postID") Long postID);

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{postID}")
    ResponseEntity<PostDTO> updatePost(@PathVariable("postID") Long postID,
                                       @RequestBody PostDTO postDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteByPostID(@RequestParam("postID") Long postID);


    @GetMapping("/byrestaurant")
    ResponseEntity<List<PostDTO>> getPostByRestaurantName(@RequestParam("restaurantName") String restaurantName);

    @GetMapping("/byrestaurant1")
    ResponseEntity<Optional<List<PostWithAllCommentDTO>>> getPostWithAllCommentByRestaurant(@RequestParam("restaurantName") String restaurantName);

}
