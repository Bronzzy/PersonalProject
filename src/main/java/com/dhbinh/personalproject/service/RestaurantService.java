package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.service.dto.RestaurantDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    Restaurant findByName(String restaurantName);

    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);

    List<RestaurantDTO> getAllRestaurant();

    RestaurantDTO getByRestaurantName(String restaurantName);

    RestaurantDTO updateRestaurant(Long restaurantID, RestaurantDTO restaurantDTO);

    void deleteByRestaurantName(String restaurantName);

    List<RestaurantDTO> getRestaurantByDishCategory(String dishCategory, String districtName, Pageable pageable);

    List<Object[]> getNumberOfRestaurantByDistrict();

    List<RestaurantDTO> getByRatingOpenHourAndClosingHour(double rating, String openHour, String closingHour);

}

