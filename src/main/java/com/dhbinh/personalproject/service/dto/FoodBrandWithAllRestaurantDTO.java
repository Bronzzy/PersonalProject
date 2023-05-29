package com.dhbinh.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodBrandWithAllRestaurantDTO {
    private FoodBrandDTO foodBrand;
    private List<RestaurantDTO> restaurants;
}
