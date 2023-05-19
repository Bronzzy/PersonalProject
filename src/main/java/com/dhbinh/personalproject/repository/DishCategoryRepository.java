package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishCategoryRepository extends JpaRepository<DishCategory, Long> {
}
