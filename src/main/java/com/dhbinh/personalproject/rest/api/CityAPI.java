package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.CityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/cities")
public interface CityAPI {

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO);

    @GetMapping
    ResponseEntity<List<CityDTO>> getAllCity();

    @GetMapping("/bycityid")
    ResponseEntity<CityDTO> getByCityID(@RequestParam("cityName") String cityName);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteCity(@RequestParam("cityName") String cityName);

}
