package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.CustomRestaurantStatisticDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/restaurants")
public interface RestaurantAPI {

    @GetMapping
    ResponseEntity<List<CustomRestaurantStatisticDTO>> getAllRestaurants();

    @GetMapping("/byrestaurantname")
    ResponseEntity<CustomRestaurantStatisticDTO> getByRestaurantName(@RequestParam("restaurantName") String restaurantName);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteByRestaurantName(@RequestParam("restaurantName") String restaurantName);

    @GetMapping("/bydishcategory")
    ResponseEntity<List<CustomRestaurantStatisticDTO>> getRestaurantByDishCategory(@RequestParam("dishCategory") String dishCategory,
                                                                                   @RequestParam("districtName") String districtName,
                                                                                   @RequestParam("limit") int limit);

    @GetMapping("/numberofrestaurantbydistrict")
    ResponseEntity<List<Object[]>> getNumberOfRestaurantByDistrict();

    @GetMapping("/byratingopenhourclosinghour")
    ResponseEntity<List<CustomRestaurantStatisticDTO>> getByRatingOpenHourAndClosingHour(@RequestParam("rating") double rating,
                                                                                         @RequestParam("openHour") String openHour,
                                                                                         @RequestParam("closingHour") String closingHour);
}
