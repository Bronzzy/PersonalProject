package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.CountryDTO;

import java.util.List;

public interface CountryService {

    CountryDTO createCountry(CountryDTO countryDTO);

    List<CountryDTO> getAllCountry();

    CountryDTO getByCountryID(String countryName);

    CountryDTO updateByCountryID(CountryDTO countryDTO);

    void deleteByCountryID(String countryName);
}
