package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.City;

import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.CityMapper;
import com.dhbinh.personalproject.mapper.CountryMapper;
import com.dhbinh.personalproject.repository.CityRepository;
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

    private final CountryMapper countryMapper;

    private final CountryService countryService;

    public CityDTO createCity(CityDTO cityDTO) {
        Optional<City> existingCity = cityRepository.findById(cityDTO.getCityName());
        if (existingCity.isPresent()) {
            throw PersonalProjectException.badRequest("CityExisted", "City is already existed");
        }



        City city = City.builder()
                .cityName(cityDTO.getCityName())
                .country(cityDTO.getCountryName())
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
                    .countryName(city.getCountry())
                    .build();

            results.add(cityDTO);
        }
        return results;
    }

    public CityDTO getByCityID(String cityName) {
        City city = cityRepository.findById(cityName).
                orElseThrow(PersonalProjectException::cityNotFound);

        return CityDTO.builder()
                .cityName(cityName)
                .countryName(city.getCountry())
                .build();
    }

//    public CityDTO updateByCityID(CityDTO cityDTO) {
//
//        City existingCity = cityRepository.findById(cityDTO.getCityName())
//                .orElseThrow(PersonalProjectException::cityNotFound);
//
//        existingCity.setCityName(cityDTO.getCityName());
//
//        return cityMapper.toDTO(cityRepository.save(existingCity));
//    }

    public void deleteByCityID(String cityName) {
        Optional<City> existingCountry = cityRepository.findById(cityName);
        if (existingCountry.isEmpty())
            throw PersonalProjectException.badRequest("CountryNotFound", "Country is not existed");

        cityRepository.delete(existingCountry.get());
    }
}
