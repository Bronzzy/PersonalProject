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
public class MonAnQuanAnDTO {

    private String dishCategory;

    private String restaurantName;

    private String address;

    private LocalTime openHour;

    private LocalTime closeHour;
}
