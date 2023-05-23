package com.dhbinh.personalproject.rest;


import com.dhbinh.personalproject.rest.api.DistrictAPI;
import com.dhbinh.personalproject.serviceimpl.DistrictServiceImpl;
import com.dhbinh.personalproject.serviceimpl.dto.DistrictDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DistrictResource implements DistrictAPI {

    private final DistrictServiceImpl districtService;
    @Override
    public ResponseEntity<List<DistrictDTO>> getAllDistrict() {
        return ResponseEntity.ok(districtService.getAllDistrict());
    }

    @Override
    public ResponseEntity<DistrictDTO> getByDistrictID(String districtName) {
        return ResponseEntity.ok(districtService.getByDistrictID(districtName));
    }

    @Override
    public ResponseEntity<Void> deleteCity(String districtName) {
        districtService.deleteByDistrictID(districtName);
        return ResponseEntity.noContent().build();
    }


}
