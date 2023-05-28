package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.FoodBrandDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/foodbrands")
public interface FoodBrandAPI {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<FoodBrandDTO> createFoodBrand(@RequestBody FoodBrandDTO foodBrandDTO);


    @GetMapping
    ResponseEntity<List<FoodBrandDTO>> getAllFoodBrand();

    @GetMapping("/byfoodbrandid")
    ResponseEntity<FoodBrandDTO> getByFoodBrandID(@RequestParam("foodBrand") String foodBrand);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteFoodBrand(@RequestParam("foodBrand") String foodBrand);
}
