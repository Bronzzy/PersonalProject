package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.Post;
import com.dhbinh.personalproject.service.dto.CustomPostStatisticDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select new com.dhbinh.personalproject.service.dto.CustomPostStatisticDTO " +
            " (p.postDate, p.adminAccount.adminName, p.restaurant.restaurantName, p.picture, p.description, p.rating)" +
            " from Post p, Restaurant r where p.restaurant.restaurantID = r.restaurantID " +
            " and r.restaurantName like :restaurantName")
    List<CustomPostStatisticDTO> getPostByRestaurantName(@Param("restaurantName") String restaurantName);
}
