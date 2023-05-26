package com.dhbinh.personalproject.rest;


import com.dhbinh.personalproject.rest.api.DistrictAPI;
import com.dhbinh.personalproject.service.DistrictService;
import com.dhbinh.personalproject.service.dto.CountryDTO;
import com.dhbinh.personalproject.service.dto.DistrictDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DistrictResource implements DistrictAPI {

    private final DistrictService districtService;

    public ResponseEntity<DistrictDTO> createDistrict(DistrictDTO districtDTO){
        log.debug("Request to create district {}", districtDTO);
        DistrictDTO createdDTO = districtService.createDistrict(districtDTO);
        return ResponseEntity.created(URI.create("/districts" + createdDTO.getName())).body(districtDTO);
    }

    @Override
    public ResponseEntity<List<DistrictDTO>> getAllDistrict() {
        return ResponseEntity.ok(districtService.getAllDistrict());
    }

//
//    @Override
//    public ResponseEntity<DistrictDTO> getByDistrictID(String districtName) {
//        return ResponseEntity.ok(districtService.getByDistrictID(districtName));
//    }
//
//    @Override
//    public ResponseEntity<Void> deleteCity(String districtName) {
//        districtService.deleteByDistrictID(districtName);
//        return ResponseEntity.noContent().build();
//    }


}
