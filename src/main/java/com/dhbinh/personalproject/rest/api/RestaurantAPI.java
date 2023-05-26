package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.RestaurantDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/restaurants")
public interface RestaurantAPI {

    @PostMapping
    ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO restaurantDTO);

    @GetMapping
    ResponseEntity<List<RestaurantDTO>> getAllRestaurants();

    @GetMapping("/byrestaurantname")
    ResponseEntity<RestaurantDTO> getByRestaurantName(@RequestParam("restaurantName") String restaurantName);

    @PutMapping("/update/{restaurantID}")
    ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable("restaurantID") Long restaurantID,
                                                   @RequestBody RestaurantDTO restaurantDTO);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteByRestaurantName(@RequestParam("restaurantName") String restaurantName);

    @GetMapping("/bydishcategory")
    ResponseEntity<List<RestaurantDTO>> getRestaurantByDishCategory(@RequestParam("dishCategory") String dishCategory,
                                                                    @RequestParam("districtName") String districtName,
                                                                    @RequestParam("limit") int limit);

    @GetMapping("/numberofrestaurantbydistrict")
    ResponseEntity<List<Object[]>> getNumberOfRestaurantByDistrict();

    @GetMapping("/byratingopenhourclosinghour")
    ResponseEntity<List<RestaurantDTO>> getByRatingOpenHourAndClosingHour(@RequestParam("rating") double rating,
                                                                          @RequestParam("openHour") String openHour,
                                                                          @RequestParam("closingHour") String closingHour);
}
