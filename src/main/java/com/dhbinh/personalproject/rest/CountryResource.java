package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.CountryAPI;
import com.dhbinh.personalproject.service.CountryService;
import com.dhbinh.personalproject.service.dto.CountryDTO;
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

    private final CountryService countryService;

    @Override
    public ResponseEntity<CountryDTO> createCountry(CountryDTO countryDTO) {
          log.debug("Request to create new country {}", countryDTO);
          CountryDTO createCountryDTO = countryService.createCountry(countryDTO);
          return ResponseEntity.created(URI.create("/countries/" + createCountryDTO.getName())).body(createCountryDTO);
      }

    @Override
    public ResponseEntity<List<CountryDTO>> getAllCountry() {
        return ResponseEntity.ok(countryService.getAllCountry());
    }

    @Override
    public ResponseEntity<CountryDTO> getByCountryID(String name) {
        return ResponseEntity.ok(countryService.getByCountryID(name));
    }

    @Override
    public ResponseEntity<CountryDTO> updateCountry(CountryDTO countryDTO) {
        log.debug("Request to update country {}", countryDTO);
        CountryDTO updateCountryDTO = countryService.updateByCountryID(countryDTO);
        return ResponseEntity.created(URI.create("/api/countries" + updateCountryDTO.getName())).body(updateCountryDTO);
    }

    @Override
    public ResponseEntity<Void> deleteCountry(String countryName) {
        countryService.deleteByCountryID(countryName);
        return ResponseEntity.noContent().build();
    }

}
