package com.dhbinh.personalproject.service.dto;

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
    private String name;
    private String countryName;
}
