package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.serviceimpl.dto.RestaurantStatisticDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/auth/restaurants")
public interface RestaurantAPI {

    @GetMapping
    ResponseEntity<List<RestaurantStatisticDTO>> getAllRestaurants();

    @GetMapping("/byrestaurantname")
    ResponseEntity<RestaurantStatisticDTO> getByRestaurantName(@RequestParam("restaurantName") String restaurantName);
}
