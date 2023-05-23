package com.dhbinh.personalproject.serviceimpl.dto;


import com.dhbinh.personalproject.entity.District;
import com.dhbinh.personalproject.entity.FoodBrand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private String restaurantName;
    private String restaurantAddress;
    private String description;
    private String telephoneNumber;
    private LocalTime openHour;
    private LocalTime closingHour;
    private String picture;
    private District district;
    private FoodBrand foodBrand;
}
