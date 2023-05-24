package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.entity.Post;
import com.dhbinh.personalproject.serviceimpl.dto.PostStatisticDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/auth/posts")
public interface PostAPI {

    @GetMapping
    ResponseEntity<List<PostStatisticDTO>> getAllPost();

    @GetMapping("/byrestaurant")
    ResponseEntity<List<PostStatisticDTO>> getPostByRestaurantName(@RequestParam("restaurantName") String restaurantName);

}
