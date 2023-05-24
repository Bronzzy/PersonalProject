package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.serviceimpl.dto.RestaurantStatisticDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/restaurants")
public interface RestaurantAPI {

    @GetMapping
    ResponseEntity<List<RestaurantStatisticDTO>> getAllRestaurants();

    @GetMapping("/byrestaurantname")
    ResponseEntity<RestaurantStatisticDTO> getByRestaurantName(@RequestParam("restaurantName") String restaurantName);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteByRestaurantName(@RequestParam("restaurantName") String restaurantName);

    @GetMapping("/bydishcategory")
    ResponseEntity<List<RestaurantStatisticDTO>> getRestaurantByDishCategory(@RequestParam("dishCategory") String dishCategory,
                                                                             @RequestParam("districtName") String districtName);
}
