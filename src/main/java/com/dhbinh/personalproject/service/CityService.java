package com.dhbinh.personalproject.serviceimpl;

import com.dhbinh.personalproject.serviceimpl.dto.CityDTO;

import java.util.List;

public interface CityService {

    List<CityDTO> getAllCity();

    CityDTO getByCityID(String cityName);

    void deleteByCityID(String cityName);
}
