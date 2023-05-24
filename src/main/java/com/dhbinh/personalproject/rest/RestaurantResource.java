package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.RestaurantAPI;
import com.dhbinh.personalproject.serviceimpl.RestaurantServiceImpl;
import com.dhbinh.personalproject.serviceimpl.dto.RestaurantStatisticDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestaurantResource implements RestaurantAPI {

    private final RestaurantServiceImpl restaurantService;
    @Override
    public ResponseEntity<List<RestaurantStatisticDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurant());
    }

    @Override
    public ResponseEntity<RestaurantStatisticDTO> getByRestaurantName(String restaurantName) {
        return ResponseEntity.ok(restaurantService.getByRestaurantName(restaurantName));
    }

    @Override
    public ResponseEntity<Void> deleteByRestaurantName(String restaurantName) {
        restaurantService.deleteByRestaurantName(restaurantName);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<RestaurantStatisticDTO>> getRestaurantByDishCategory(String dishCategory, String districtName) {
        return ResponseEntity.ok(restaurantService.getRestaurantByDishCategory(dishCategory, districtName));
    }
}
