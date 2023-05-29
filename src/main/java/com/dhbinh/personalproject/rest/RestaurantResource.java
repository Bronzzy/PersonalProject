package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.RestaurantAPI;
import com.dhbinh.personalproject.service.RestaurantService;
import com.dhbinh.personalproject.service.dto.NumberOfRestaurantByDistrictDTO;
import com.dhbinh.personalproject.service.dto.RestaurantByRatingAndDistrictDTO;
import com.dhbinh.personalproject.service.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestaurantResource implements RestaurantAPI {

    private final RestaurantService restaurantService;

    @Override
    public ResponseEntity<RestaurantDTO> createRestaurant(RestaurantDTO restaurantDTO) {
        log.debug("Request to create new country {}", restaurantDTO);
        RestaurantDTO dto = restaurantService.createRestaurant(restaurantDTO);
        return ResponseEntity.created(URI.create("/api/restaurants/" + dto.getID())).body(dto);
    }

    @Override
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurant());
    }

    @Override
    public ResponseEntity<RestaurantDTO> getByRestaurantName(String restaurantName) {
        return ResponseEntity.ok(restaurantService.getByRestaurantName(restaurantName));
    }

    @Override
    public ResponseEntity<RestaurantDTO> updateRestaurant(Long restaurantID, RestaurantDTO restaurantDTO) {
        log.debug("Request to update restaurant {}", restaurantDTO);
        RestaurantDTO dto = restaurantService.updateRestaurant(restaurantID, restaurantDTO);
        return ResponseEntity.created(URI.create("/api/restaurants/" + dto.getID())).body(dto);
    }

    @Override
    public ResponseEntity<Void> deleteByRestaurantName(String restaurantName) {
        restaurantService.deleteByRestaurantName(restaurantName);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<RestaurantDTO>> getRestaurantByDishCategory(String dishCategory, String districtName, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<RestaurantDTO> restaurants = restaurantService.getRestaurantByDishCategory(dishCategory, districtName, pageable);

        if (!restaurants.isEmpty()) {
            return ResponseEntity.ok(restaurants);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<NumberOfRestaurantByDistrictDTO>> getNumberOfRestaurantByDistrict() {
        return ResponseEntity.ok(restaurantService.getNumberOfRestaurantByDistrict());
    }

    @Override
    public ResponseEntity<List<RestaurantDTO>> getByRatingOpenHourAndClosingHour(double rating, String openHour, String closingHour) {
        return ResponseEntity.ok(restaurantService.getByRatingOpenHourAndClosingHour(rating, openHour, closingHour));
    }

    @Override
    public ResponseEntity<List<RestaurantDTO>> getRestaurantByPrice(double startingPrice, double endingPrice) {
        return ResponseEntity.ok(restaurantService.getRestaurantByPrice(startingPrice, endingPrice));
    }

    @Override
    public ResponseEntity<List<RestaurantByRatingAndDistrictDTO>> getRestaurantByRatingAndDistrict(double rating, String districtName) {
        return ResponseEntity.ok(restaurantService.getRestaurantByRatingAndDistrict(rating, districtName));
    }
}

