package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.Comment;
import com.dhbinh.personalproject.service.dto.MonthlyUserCountDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(" SELECT c " +
            " FROM Comment c, UserAccount ua " +
            " WHERE c.userAccount.ID = ua.ID " +
            " AND ua.username like :username")
    List<Comment> getByUsername(@Param("username") String username);

    @Query(" SELECT count(c.id), ua.username" +
            " FROM Comment c, UserAccount ua " +
            " WHERE c.userAccount.ID =  ua.ID" +
            " GROUP BY ua.username " +
            " ORDER BY count(c.id) DESC " )
    List<Object[]> getTopUserWithMostComment(Pageable pageable);

    @Query(" SELECT new com.dhbinh.personalproject.service.dto.MonthlyUserCountDTO(FUNCTION('YEAR', ura.assignedDate),FUNCTION('MONTH', ura.assignedDate), COUNT(ua.id)) " +
            " FROM UserAccount ua, UserRoleAssignment ura " +
            " WHERE ua.id = ura.userAccounts.id " +
            " GROUP BY FUNCTION('YEAR', ura.assignedDate), FUNCTION('MONTH', ura.assignedDate)" +
            " ORDER BY FUNCTION('YEAR', ura.assignedDate), FUNCTION('MONTH', ura.assignedDate)")
    List<MonthlyUserCountDTO> getMonthlyUserCount();
}
