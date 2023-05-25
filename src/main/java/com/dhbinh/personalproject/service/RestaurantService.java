package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.CustomRestaurantStatisticDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {

    List<CustomRestaurantStatisticDTO> getAllRestaurant();

    CustomRestaurantStatisticDTO getByRestaurantName(String restaurantName);

    void deleteByRestaurantName(String restaurantName);

    List<CustomRestaurantStatisticDTO> getRestaurantByDishCategory(String dishCategory, String districtName, Pageable pageable);

    List<Object[]> getNumberOfRestaurantByDistrict();

    List<CustomRestaurantStatisticDTO> getByRatingOpenHourAndClosingHour(double rating, String openHour, String closingHour);

}

