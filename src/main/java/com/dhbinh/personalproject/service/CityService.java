package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.CityDTO;

import java.util.List;

public interface CityService {

    CityDTO createCity(CityDTO cityDTO);

    List<CityDTO> getAllCity();

    CityDTO getByCityID(String cityName);

    CityDTO updateCity(String cityID, CityDTO cityDTO);

    void deleteByCityID(String cityName);
}
