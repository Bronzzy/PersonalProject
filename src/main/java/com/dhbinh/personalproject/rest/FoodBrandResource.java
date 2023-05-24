package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.FoodBrandAPI;
import com.dhbinh.personalproject.serviceimpl.FoodBrandService;
import com.dhbinh.personalproject.serviceimpl.dto.FoodBrandDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FoodBrandResource implements FoodBrandAPI {

    private final FoodBrandService foodBrandSerVice;
    @Override
    public ResponseEntity<List<FoodBrandDTO>> getAllFoodBrand() {
        return ResponseEntity.ok(foodBrandSerVice.getAllFoodBrand());
    }

    @Override
    public ResponseEntity<FoodBrandDTO> getByFoodBrandID(String foodBrand) {
        return ResponseEntity.ok(foodBrandSerVice.getByFoodBrandID(foodBrand));
    }

    @Override
    public ResponseEntity<Void> deleteFoodBrand(String foodBrand) {
        foodBrandSerVice.deleteByFoodBrandID(foodBrand);
        return ResponseEntity.noContent().build();
    }
}
