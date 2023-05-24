package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.serviceimpl.dto.DistrictDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/districts")
public interface DistrictAPI {

    @GetMapping
    ResponseEntity<List<DistrictDTO>> getAllDistrict();

    @GetMapping("/bycityid")
    ResponseEntity<DistrictDTO> getByDistrictID(@RequestParam("districtName") String districtName);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteCity(@RequestParam("districtName") String districtName);

}
