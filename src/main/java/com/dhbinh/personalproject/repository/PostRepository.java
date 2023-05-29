package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.Post;
import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.service.dto.PostWithAllCommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByRestaurant(Restaurant restaurant);

}
