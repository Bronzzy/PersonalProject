package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.serviceimpl.dto.CountryDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/countries")
public interface CountryAPI {

    @PostMapping
    ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO);

    @GetMapping
    ResponseEntity <List<CountryDTO>> getAllCountry();

    @GetMapping("/getcountrybyname")
    ResponseEntity<CountryDTO> getCountryByCountryName(@Param("countryName") String countryName);

    @PostMapping("/{countryid}")
    ResponseEntity<CountryDTO> updateCountry(@PathVariable
                                             @RequestBody CountryDTO countryDTO);

    @DeleteMapping("/{countryid}")
    ResponseEntity<Void> deleteCountry(@PathVariable("countryName") String countryName);
}
