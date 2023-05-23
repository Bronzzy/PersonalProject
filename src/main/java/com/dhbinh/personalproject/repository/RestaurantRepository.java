package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> getByRestaurantName(String restaurantName);
}
