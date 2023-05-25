package com.dhbinh.personalproject.service.dto;

import com.dhbinh.personalproject.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO {

    private String districtName;

    private City cityName;

}
