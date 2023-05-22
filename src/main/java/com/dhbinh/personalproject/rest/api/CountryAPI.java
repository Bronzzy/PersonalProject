package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.serviceimpl.dto.CountryDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/countries")
public interface CountryAPI {

    @PostMapping
    ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    ResponseEntity<List<CountryDTO>> getAllCountry();

    @GetMapping("/getcountrybyname")
    ResponseEntity<Optional<CountryDTO>> getByCountryID(@Param("countryName") String countryName);

    @PostMapping("/{countryid}")
    ResponseEntity<CountryDTO> updateCountry(@PathVariable
                                             @RequestBody CountryDTO countryDTO);

    @DeleteMapping("/{countryid}")
    ResponseEntity<Void> deleteCountry(@PathVariable("countryName") String countryName);
}
