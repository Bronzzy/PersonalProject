package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.FoodBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodBrandRepository extends JpaRepository<FoodBrand, String> {
}
