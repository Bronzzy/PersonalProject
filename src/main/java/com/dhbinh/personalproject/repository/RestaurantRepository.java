package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.serviceimpl.dto.RestaurantStatisticDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> getByRestaurantName(String restaurantName);

    @Query(" select distinct r " +
            " from Restaurant r, Menu m, DishCategory dc, District d, FoodBrand f " +
            " where dc.dishCategoryID = m.dishCategory.dishCategoryID " +
            " and m.restaurant.restaurantID = r.restaurantID " +
            " and r.district.districtName = d.districtName " +
            " and dc.dishCategory like :dishCategory " +
            " and d.districtName like :districtName")
    List<Restaurant> getRestaurantByDishCategory(@Param("dishCategory") String dishCategory,
                                                 @Param("districtName") String districtName);
}
