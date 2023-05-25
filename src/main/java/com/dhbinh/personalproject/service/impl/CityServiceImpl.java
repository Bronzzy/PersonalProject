package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.City;

import com.dhbinh.personalproject.entity.Country;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.CityMapper;
import com.dhbinh.personalproject.mapper.CountryMapper;
import com.dhbinh.personalproject.repository.CityRepository;
import com.dhbinh.personalproject.repository.CountryRepository;
import com.dhbinh.personalproject.rest.api.CityAPI;
import com.dhbinh.personalproject.service.CityService;
import com.dhbinh.personalproject.service.CountryService;
import com.dhbinh.personalproject.service.dto.CityDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    private final CountryService countryService;

    private final CountryRepository countryRepository;

    public CityDTO createCity(CityDTO cityDTO) {

        if (cityRepository.existsById(cityDTO.getCityName()))
            throw PersonalProjectException.badRequest("CityNameExisted", "City name is already existed");

        if (!countryRepository.existsById(cityDTO.getCountryName()))
            throw PersonalProjectException.countryNotFound();

        City city = City.builder()
                .cityName(cityDTO.getCityName())
                .country(countryRepository.findById(cityDTO.getCountryName()).get())
                .build();

        return cityMapper.toDTO(cityRepository.save(city));
    }

    public List<CityDTO> getAllCity() {

        List<CityDTO> results = new ArrayList<>();

        List<City> cityList = cityRepository.findAll();
        if (cityList.isEmpty())
            throw PersonalProjectException.cityNotFound();

        for (City city : cityList) {
            CityDTO cityDTO = CityDTO.builder()
                    .cityName(city.getCityName())
                    .countryName(city.getCountry().getCountryName())
                    .build();

            results.add(cityDTO);
        }
        return results;
    }

    public CityDTO getByCityID(String cityName) {
        City city = cityRepository.findById(cityName).
                orElseThrow(PersonalProjectException::cityNotFound);

        return cityMapper.toDTO(city);
    }

    public CityDTO updateCity(String cityID,CityDTO cityDTO) {

        City existingCity = cityRepository.findById(cityID)
                .orElseThrow(PersonalProjectException::cityNotFound);

        Country existingCountry = countryRepository.findById(cityDTO.getCountryName())
                .orElseThrow(PersonalProjectException::countryNotFound);

        existingCity.setCityName(cityDTO.getCityName());
        existingCity.setCountry(existingCountry);

        return cityMapper.toDTO(cityRepository.save(existingCity));
    }

    public void deleteByCityID(String cityName) {
        Optional<City> existingCountry = cityRepository.findById(cityName);
        if (existingCountry.isEmpty())
            throw PersonalProjectException.badRequest("CountryNotFound", "Country is not existed");

        cityRepository.delete(existingCountry.get());
    }
}
