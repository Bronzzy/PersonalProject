package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.DishCategoryDTO;

import java.util.List;

public interface DishCategoryService {
    DishCategoryDTO createDishCategory(DishCategoryDTO dishCategoryDTO);

    List<DishCategoryDTO> getAllDishCategory();

    DishCategoryDTO getByDishCategory(String dishCategory);

    DishCategoryDTO updateDishCategory(Long dishCategoryID, DishCategoryDTO dishCategoryDTO);

    void deleteByDishCategory(String dishCategory);
}
