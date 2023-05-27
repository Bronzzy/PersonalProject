package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.CountryDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/countries")
public interface CountryAPI {


    @PostMapping
    ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO);

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    ResponseEntity<List<CountryDTO>> getAllCountry();

    @GetMapping("/bycountryid")
    ResponseEntity<CountryDTO> getByCountryID(@Param("countryName") String countryName);

    @PostMapping("/{countryid}")
    ResponseEntity<CountryDTO> updateCountry(@PathVariable
                                             @RequestBody CountryDTO countryDTO);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteCountry(@RequestParam("countryName") String countryName);
}
