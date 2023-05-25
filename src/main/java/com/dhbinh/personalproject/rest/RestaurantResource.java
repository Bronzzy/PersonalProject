package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.RestaurantAPI;
import com.dhbinh.personalproject.service.RestaurantService;
import com.dhbinh.personalproject.service.dto.CustomRestaurantStatisticDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestaurantResource implements RestaurantAPI {

    private final RestaurantService restaurantService;

    @Override
    public ResponseEntity<List<CustomRestaurantStatisticDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurant());
    }

    @Override
    public ResponseEntity<CustomRestaurantStatisticDTO> getByRestaurantName(String restaurantName) {
        return ResponseEntity.ok(restaurantService.getByRestaurantName(restaurantName));
    }

    @Override
    public ResponseEntity<Void> deleteByRestaurantName(String restaurantName) {
        restaurantService.deleteByRestaurantName(restaurantName);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<CustomRestaurantStatisticDTO>> getRestaurantByDishCategory(String dishCategory, String districtName, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<CustomRestaurantStatisticDTO> restaurants = restaurantService.getRestaurantByDishCategory(dishCategory, districtName, pageable);

        if (!restaurants.isEmpty()) {
            return ResponseEntity.ok(restaurants);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Object[]>> getNumberOfRestaurantByDistrict() {
        return ResponseEntity.ok(restaurantService.getNumberOfRestaurantByDistrict());
    }

    @Override
    public ResponseEntity<List<CustomRestaurantStatisticDTO>> getByRatingOpenHourAndClosingHour(double rating, String openHour, String closingHour) {
        return ResponseEntity.ok(restaurantService.getByRatingOpenHourAndClosingHour(rating, openHour, closingHour));
    }


}

