package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(" SELECT p " +
            " FROM Post p, Restaurant r " +
            " WHERE p.restaurant.ID = r.ID " +
            " AND r.name like :restaurantName")
    Optional<List<Post>> getPostByRestaurantName(@Param("restaurantName") String restaurantName);
}
