package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.DishCategoryAPI;
import com.dhbinh.personalproject.service.DishCategoryService;
import com.dhbinh.personalproject.service.dto.DishCategoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DishCategoryResource implements DishCategoryAPI {

    private final DishCategoryService dishCategoryService;

    @Override
    public ResponseEntity<DishCategoryDTO> createDishCategory(DishCategoryDTO dishCategoryDTO) {
        DishCategoryDTO dto = dishCategoryService.createDishCategory(dishCategoryDTO);
        return ResponseEntity.created(URI.create("/api/dishcategories/" + dto.getID())).body(dto);
    }

    @Override
    public ResponseEntity<List<DishCategoryDTO>> getAllDishCategory() {
        return ResponseEntity.ok(dishCategoryService.getAllDishCategory());
    }

    @Override
    public ResponseEntity<DishCategoryDTO> getDishCategoryByDishCategory(String dishCategory) {
        return ResponseEntity.ok(dishCategoryService.getByType(dishCategory));
    }

    @Override
    public ResponseEntity<Void> deleteDishCategory(String dishCategory) {
        dishCategoryService.deleteByType(dishCategory);
        return ResponseEntity.noContent().build();
    }
}
