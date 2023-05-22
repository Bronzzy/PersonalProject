package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,String> {
}
