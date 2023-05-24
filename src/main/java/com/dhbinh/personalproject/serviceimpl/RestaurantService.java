package com.dhbinh.personalproject.serviceimpl;

import com.dhbinh.personalproject.serviceimpl.dto.RestaurantStatisticDTO;

import java.util.List;

public interface RestaurantService {

    List<RestaurantStatisticDTO> getAllRestaurant();
    RestaurantStatisticDTO getByRestaurantName(String restaurantName);
    void deleteByRestaurantName(String restaurantName);
    List<RestaurantStatisticDTO> getRestaurantByDishCategory(String dishCategory, String districtName);
}

