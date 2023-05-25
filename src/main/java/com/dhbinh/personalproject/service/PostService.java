package com.dhbinh.personalproject.serviceimpl;

import com.dhbinh.personalproject.serviceimpl.dto.PostStatisticDTO;

import java.util.List;

public interface PostService {

    List<PostStatisticDTO> getAllPost();

    List<PostStatisticDTO> getPostByRestaurantName(String restaurantName);

    void deleteByPostID(Long postID);
}
