package com.dhbinh.personalproject.service.dto;


import com.dhbinh.personalproject.entity.District;
import com.dhbinh.personalproject.entity.FoodBrand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private Long ID;

    private String name;

    private String address;

    private String description;

    private String phoneNumber;

    private LocalTime openHour;

    private LocalTime closingHour;

    private String picture;

    private String districtName;

    private String foodBrand;
}
