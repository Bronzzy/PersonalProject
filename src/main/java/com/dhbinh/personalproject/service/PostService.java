package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.PostDTO;
import com.dhbinh.personalproject.service.dto.RestaurantDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPost();

    PostDTO getPostByID(Long postID);

    PostDTO updatePost(Long postID, PostDTO postDTO);

    List<PostDTO> getPostByRestaurantName(String restaurantName);

    void deleteByPostID(Long postID);

}
