package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.DistrictDTO;

import java.util.List;
public interface DistrictService {

    List<DistrictDTO> getAllDistrict();

    DistrictDTO getByDistrictID(String districtName);

    void deleteByDistrictID(String districtName);
}
