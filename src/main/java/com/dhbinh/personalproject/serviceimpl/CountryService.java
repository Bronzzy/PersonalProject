package com.dhbinh.personalproject.serviceimpl;

import com.dhbinh.personalproject.entity.Country;
import com.dhbinh.personalproject.serviceimpl.dto.CountryDTO;

import java.util.List;

public interface CountryService {

    CountryDTO createCountry(CountryDTO countryDTO);

    List<CountryDTO> getAllCountry();

    CountryDTO getByCountryID(String countryName);

    CountryDTO updateByCountryID(CountryDTO countryDTO);

    void deleteByCountryID(String countryName);
}
