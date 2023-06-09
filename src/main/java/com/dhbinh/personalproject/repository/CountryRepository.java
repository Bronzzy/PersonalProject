package com.dhbinh.personalproject.repository;

import com.dhbinh.personalproject.entity.Country;
import com.dhbinh.personalproject.serviceimpl.dto.CountryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    Optional<Country> getCountryByCountryName(String name);
}
