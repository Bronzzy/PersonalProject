package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.PostDTO;
import com.dhbinh.personalproject.service.dto.RestaurantDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/posts")
public interface PostAPI {

    @PostMapping
    ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO);

    @GetMapping
    ResponseEntity<List<PostDTO>> getAllPost();

    @GetMapping("/bypostid")
    ResponseEntity<PostDTO> getByPostID(@RequestParam("postID") Long postID);

    @PutMapping("/update/{postID}")
    ResponseEntity<PostDTO> updatePost(@PathVariable("postID") Long postID,
                                       @RequestBody PostDTO postDTO);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteByPostID(@RequestParam("postID") Long postID);

    @GetMapping("/byrestaurant")
    ResponseEntity<List<PostDTO>> getPostByRestaurantName(@RequestParam("restaurantName") String restaurantName);

}
