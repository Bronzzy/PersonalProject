package com.dhbinh.personalproject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private Long menuID;
    private String restaurantName;
    private String dishCategory;
    private String ingredients;
    private Double startingPrice;
    private Double endingPrice;
    private String description;
}
