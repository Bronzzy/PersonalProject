package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.Post;
import com.dhbinh.personalproject.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByRestaurant(Restaurant restaurant);
}
