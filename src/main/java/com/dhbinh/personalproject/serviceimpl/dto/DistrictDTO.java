package com.dhbinh.personalproject.serviceimpl.dto;

import com.dhbinh.personalproject.entity.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO {

    private String districtName;

    private City city;

}
