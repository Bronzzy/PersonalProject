package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.CustomPostStatisticDTO;

import java.util.List;

public interface PostService {

    List<CustomPostStatisticDTO> getAllPost();

    List<CustomPostStatisticDTO> getPostByRestaurantName(String restaurantName);

    void deleteByPostID(Long postID);
}
