package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.DistrictDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/districts")
public interface DistrictAPI {

    @PostMapping
    ResponseEntity<DistrictDTO> createDistrict(@RequestBody DistrictDTO districtDTO);

    @GetMapping
    ResponseEntity<List<DistrictDTO>> getAllDistrict();

    @GetMapping("/bycityid")
    ResponseEntity<DistrictDTO> getByDistrictID(@RequestParam("districtName") String districtName);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteCity(@RequestParam("districtName") String districtName);

}
