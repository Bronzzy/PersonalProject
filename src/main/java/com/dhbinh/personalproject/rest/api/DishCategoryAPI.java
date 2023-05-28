package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.DishCategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dishcategories")
public interface DishCategoryAPI {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<DishCategoryDTO> createDishCategory(@RequestBody DishCategoryDTO dishCategoryDTO);

    @GetMapping
    ResponseEntity<List<DishCategoryDTO>> getAllDishCategory();

    @GetMapping("/bydishcategory")
    ResponseEntity<DishCategoryDTO> getDishCategoryByDishCategory(@RequestParam("dishCategory") String dishCategory);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteDishCategory(@RequestParam("dishCategory") String dishCategory);
}
