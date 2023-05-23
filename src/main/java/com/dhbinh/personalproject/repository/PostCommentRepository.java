package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends JpaRepository<Comment, Long> {
}
