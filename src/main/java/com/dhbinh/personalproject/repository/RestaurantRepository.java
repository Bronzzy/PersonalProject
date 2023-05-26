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
    Optional<Restaurant> findByName(String restaurantName);

    @Query(" SELECT distinct r " +
            " FROM Restaurant r, Menu m, DishCategory dc, District d, FoodBrand f " +
            " WHERE dc.ID = m.dishCategory.ID " +
            " AND m.restaurant.ID = r.ID " +
            " AND r.district.name = d.name " +
            " AND dc.type like :type " +
            " AND d.name like :name")
    List<Restaurant> getRestaurantByDishCategory(@Param("type") String dishCategory,
                                                 @Param("name") String districtName,
                                                 Pageable pageable);

    @Query(" SELECT count(r.ID), d.name" +
            " FROM Restaurant r, District d" +
            " WHERE r.district.name = d.name" +
            " group by d.name")
    List<Object[]> getNumberOfRestaurantByDistrict();

    @Query(" SELECT distinct r " +
            " FROM Restaurant r, Post p " +
            " WHERE r.ID = p.restaurant.ID " +
            " AND p.rating > :rating " +
            " AND r.openHour > :openHour " +
            " AND r.closingHour < :closingHour")
    List<Restaurant> getByRatingOpenHourAndClosingHour(@Param("rating") double rating,
                                                       @Param("openHour") LocalTime openHour,
                                                       @Param("closingHour") LocalTime closingHour);
}
