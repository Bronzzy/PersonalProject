package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishCategoryRepository extends JpaRepository<DishCategory, Long> {

    Boolean existsByType(String type);

    Optional<DishCategory> findByType(String type);

    void deleteByType(String type);
}
