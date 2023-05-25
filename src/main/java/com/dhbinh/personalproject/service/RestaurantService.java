package com.dhbinh.personalproject.serviceimpl;

import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.serviceimpl.dto.RestaurantStatisticDTO;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.List;

public interface RestaurantService {

    List<RestaurantStatisticDTO> getAllRestaurant();

    RestaurantStatisticDTO getByRestaurantName(String restaurantName);

    void deleteByRestaurantName(String restaurantName);

    List<RestaurantStatisticDTO> getRestaurantByDishCategory(String dishCategory, String districtName, Pageable pageable);

    List<Object[]> getNumberOfRestaurantByDistrict();

    List<RestaurantStatisticDTO> getByRatingOpenHourAndClosingHour(double rating, String openHour, String closingHour);

}

