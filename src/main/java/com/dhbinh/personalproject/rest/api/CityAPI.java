package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.CityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cities")
public interface CityAPI {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO);

    @GetMapping
    ResponseEntity<List<CityDTO>> getAllCity();

    @GetMapping("/bycityid")
    ResponseEntity<CityDTO> getByCityID(@RequestParam("cityName") String cityName);

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{cityid}")
    ResponseEntity<CityDTO> updateCity(@PathVariable("cityid") String cityID,
                                       @RequestBody CityDTO cityDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteCity(@RequestParam("cityName") String cityName);

}
