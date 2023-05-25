package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.FoodBrandDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/foodbrands")
public interface FoodBrandAPI {

    @GetMapping
    ResponseEntity<List<FoodBrandDTO>> getAllFoodBrand();

    @GetMapping("/byfoodbrandid")
    ResponseEntity<FoodBrandDTO> getByFoodBrandID(@RequestParam("foodBrand") String foodBrand);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteFoodBrand(@RequestParam("foodBrand") String foodBrand);
}
