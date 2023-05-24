package com.dhbinh.personalproject.serviceimpl.dto;

import com.dhbinh.personalproject.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
    private String cityName;
    private Country country;
}
