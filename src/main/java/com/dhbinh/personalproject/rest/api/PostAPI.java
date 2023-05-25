package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.CustomPostStatisticDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/posts")
public interface PostAPI {

    @GetMapping
    ResponseEntity<List<CustomPostStatisticDTO>> getAllPost();

    @GetMapping("/byrestaurant")
    ResponseEntity<List<CustomPostStatisticDTO>> getPostByRestaurantName(@RequestParam("restaurantName") String restaurantName);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteByPostID(@RequestParam("postID") Long postID);

}
