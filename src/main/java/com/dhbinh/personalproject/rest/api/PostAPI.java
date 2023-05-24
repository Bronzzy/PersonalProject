package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.entity.Post;
import com.dhbinh.personalproject.serviceimpl.dto.PostStatisticDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/posts")
public interface PostAPI {

    @GetMapping
    ResponseEntity<List<PostStatisticDTO>> getAllPost();

    @GetMapping("/byrestaurant")
    ResponseEntity<List<PostStatisticDTO>> getPostByRestaurantName(@RequestParam("restaurantName") String restaurantName);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteByPostID(@RequestParam("postID") Long postID);

}
