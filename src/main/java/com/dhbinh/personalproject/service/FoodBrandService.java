package com.dhbinh.personalproject.serviceimpl;

import com.dhbinh.personalproject.serviceimpl.dto.FoodBrandDTO;

import java.util.List;

public interface FoodBrandService {

    List<FoodBrandDTO> getAllFoodBrand();
    FoodBrandDTO getByFoodBrandID(String foodBrand);
    void deleteByFoodBrandID(String foodBrand);
}
