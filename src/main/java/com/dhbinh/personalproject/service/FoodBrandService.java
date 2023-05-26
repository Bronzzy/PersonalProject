package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.FoodBrandDTO;

import java.util.List;

public interface FoodBrandService {

    FoodBrandDTO createFoodBrand(FoodBrandDTO foodBrandDTO);

    List<FoodBrandDTO> getAllFoodBrand();

    FoodBrandDTO getByFoodBrandID(String foodBrand);

    void deleteByFoodBrandID(String foodBrand);
}
