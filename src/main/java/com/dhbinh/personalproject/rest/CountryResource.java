package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.CountryAPI;
import com.dhbinh.personalproject.serviceimpl.CountryServiceImpl;
import com.dhbinh.personalproject.serviceimpl.dto.CountryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CountryResource implements CountryAPI {
    private final CountryServiceImpl countryService;

    @Override
    public ResponseEntity<CountryDTO> createCountry(CountryDTO countryDTO) {
        log.debug("Request to create new country {}", countryDTO);
        CountryDTO createCountryDTO = countryService.createCountry(countryDTO);
        return ResponseEntity.created(URI.create("/api/countries/" + createCountryDTO.getCountryName())).body(createCountryDTO);
    }

    @Override
    public ResponseEntity<List<CountryDTO>> getAllCountry() {
        return ResponseEntity.ok(countryService.getAllCountry());
    }

    @Override
    public ResponseEntity<CountryDTO> getCountryByName() {
        return ResponseEntity.ok(countryService.getCountryByName());
    }

    @Override
    public ResponseEntity<CountryDTO> updateCountry(CountryDTO countryDTO) {
        log.debug("Request to update country {}", countryDTO);
        CountryDTO updateCountryDTO = countryService.updateCountry(countryDTO);
        return ResponseEntity.created(URI.create("/api/countries" + updateCountryDTO.getCountryName())).body(updateCountryDTO);
    }


}
