package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> getByRestaurantName(String restaurantName);

    @Query(" SELECT distinct r " +
            " FROM Restaurant r, Menu m, DishCategory dc, District d, FoodBrand f " +
            " WHERE dc.dishCategoryID = m.dishCategory.dishCategoryID " +
            " AND m.restaurant.restaurantID = r.restaurantID " +
            " AND r.district.districtName = d.districtName " +
            " AND dc.dishCategory like :dishCategory " +
            " AND d.districtName like :districtName")
    List<Restaurant> getRestaurantByDishCategory(@Param("dishCategory") String dishCategory,
                                                 @Param("districtName") String districtName,
                                                 Pageable pageable);

    @Query(" SELECT count(r.restaurantID), d.districtName" +
            " FROM Restaurant r, District d" +
            " WHERE r.district.districtName = d.districtName" +
            " group by d.districtName")
    List<Object[]> getNumberOfRestaurantByDistrict();

    @Query(" SELECT distinct r " +
            " FROM Restaurant r, Post p " +
            " WHERE r.restaurantID = p.restaurant.restaurantID " +
            " AND p.rating > :rating " +
            " AND r.openHour > :openHour " +
            " AND r.closingHour < :closingHour")
    List<Restaurant> getByRatingOpenHourAndClosingHour(@Param("rating") double rating,
                                                       @Param("openHour") LocalTime openHour,
                                                       @Param("closingHour") LocalTime closingHour);
}
