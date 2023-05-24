package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.CityAPI;
import com.dhbinh.personalproject.serviceimpl.CityService;
import com.dhbinh.personalproject.serviceimpl.dto.CityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CityResource implements CityAPI {

    private final CityService cityService;


//    @Override
//    public ResponseEntity<CityDTO> createCity(CityDTO cityDTO) {
//        log.debug("Request to create new city{}", cityDTO);
//        CityDTO createCityDTO = cityService.createCity(cityDTO);
//        return ResponseEntity.created(URI.create("/cities/" + createCityDTO.getCityName())).body(createCityDTO);
//    }

    @Override
    public ResponseEntity<List<CityDTO>> getAllCity() {
        return ResponseEntity.ok(cityService.getAllCity());
    }

    @Override
    public ResponseEntity <CityDTO> getByCityID(String cityName) {
        return ResponseEntity.ok(cityService.getByCityID(cityName));
    }

    @Override
    public ResponseEntity<Void> deleteCity(String cityName) {
        cityService.deleteByCityID(cityName);
        return ResponseEntity.noContent().build();
    }

}
