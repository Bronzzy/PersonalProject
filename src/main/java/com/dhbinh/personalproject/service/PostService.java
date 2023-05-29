package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.PostDTO;
import com.dhbinh.personalproject.service.dto.PostWithAllCommentDTO;
import com.dhbinh.personalproject.service.dto.RestaurantDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPost();

    PostDTO getPostByID(Long postID);

    PostDTO updatePost(Long postID, PostDTO postDTO);

    void deleteByPostID(Long postID);

    PostWithAllCommentDTO getPostWithAllCommentByRestaurantName(String restaurantName);
}
