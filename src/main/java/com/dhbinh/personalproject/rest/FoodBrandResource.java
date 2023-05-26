package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.FoodBrandAPI;
import com.dhbinh.personalproject.service.FoodBrandService;
import com.dhbinh.personalproject.service.dto.FoodBrandDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FoodBrandResource implements FoodBrandAPI {

    private final FoodBrandService foodBrandService;

    @Override
    public ResponseEntity<FoodBrandDTO> createFoodBrand(FoodBrandDTO foodBrandDTO) {
        log.debug("Request to create food brand {}",foodBrandDTO);
        FoodBrandDTO dto = foodBrandService.createFoodBrand(foodBrandDTO);
        return ResponseEntity.created(URI.create("/api/foodbrands/" + dto.getFoodBrand())).body(dto);
    }

    @Override
    public ResponseEntity<List<FoodBrandDTO>> getAllFoodBrand() {
        return ResponseEntity.ok(foodBrandService.getAllFoodBrand());
    }

    @Override
    public ResponseEntity<FoodBrandDTO> getByFoodBrandID(String foodBrand) {
        return ResponseEntity.ok(foodBrandService.getByFoodBrandID(foodBrand));
    }

    @Override
    public ResponseEntity<Void> deleteFoodBrand(String foodBrand) {
        foodBrandService.deleteByFoodBrandID(foodBrand);
        return ResponseEntity.noContent().build();
    }
}
