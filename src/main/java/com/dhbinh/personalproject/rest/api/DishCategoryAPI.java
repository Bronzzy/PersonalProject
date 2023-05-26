package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.DishCategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/dishcategories")
public interface DishCategoryAPI {

    @PostMapping
    ResponseEntity<DishCategoryDTO> createDishCategory(@RequestBody DishCategoryDTO dishCategoryDTO);

    @GetMapping
    ResponseEntity<List<DishCategoryDTO>> getAllDishCategory();

    @GetMapping("/bydishcategory")
    ResponseEntity<DishCategoryDTO> getDishCategoryByDishCategory(@RequestParam("dishCategory") String dishCategory);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteDishCategory(@RequestParam("dishCategory") String dishCategory);
}
