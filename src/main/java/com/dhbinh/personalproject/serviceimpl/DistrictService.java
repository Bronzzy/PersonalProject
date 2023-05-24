package com.dhbinh.personalproject.serviceimpl;

import com.dhbinh.personalproject.serviceimpl.dto.DistrictDTO;

import java.util.List;
public interface DistrictService {

    List<DistrictDTO> getAllDistrict();

    DistrictDTO getByDistrictID(String districtName);

    void deleteByDistrictID(String districtName);
}
