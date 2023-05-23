package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.serviceimpl.dto.CityDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/auth/cities")
public interface CityAPI {

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO);

    @GetMapping
    ResponseEntity<List<CityDTO>> getAllCity();

    @GetMapping("/bycityid")
    ResponseEntity<CityDTO> getByCityID(@RequestParam("cityName") String cityName);

    @DeleteMapping("/delete/{cityName}")
    ResponseEntity<Void> deleteCity(@PathVariable("cityName") String cityName);

}
