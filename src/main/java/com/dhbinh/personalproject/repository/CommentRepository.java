package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(" SELECT c " +
            " FROM Comment c, UserAccount ua " +
            " WHERE c.userAccount.userAccountID = ua.userAccountID " +
            " AND ua.username like :username")
    List<Comment> getByUsername(@Param("username") String username);


}
