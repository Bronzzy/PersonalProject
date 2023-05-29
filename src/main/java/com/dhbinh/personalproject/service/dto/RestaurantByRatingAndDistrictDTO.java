package com.dhbinh.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantByRatingAndDistrictDTO {

    private String restaurantName;

    private String address;

    private String district;

    private LocalTime openHour;

    private LocalTime closeHour;

    private Double rating;
}
