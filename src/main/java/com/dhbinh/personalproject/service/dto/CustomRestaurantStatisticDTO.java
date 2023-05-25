package com.dhbinh.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomRestaurantStatisticDTO {
    private String restaurantName;
    private String restaurantAddress;
    private String description;
    private String telephoneNumber;
    private LocalTime openHour;
    private LocalTime closingHour;
    private String picture;
    private String district;
    private String foodBrand;
}
