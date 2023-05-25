package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.CityDTO;

import java.util.List;

public interface CityService {

    List<CityDTO> getAllCity();

    CityDTO getByCityID(String cityName);

    void deleteByCityID(String cityName);
}
