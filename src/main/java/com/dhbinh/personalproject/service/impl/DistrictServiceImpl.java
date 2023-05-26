package com.dhbinh.personalproject.service.impl;


import com.dhbinh.personalproject.entity.District;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.DistrictMapper;
import com.dhbinh.personalproject.repository.CityRepository;
import com.dhbinh.personalproject.repository.DistrictRepository;
import com.dhbinh.personalproject.service.DistrictService;
import com.dhbinh.personalproject.service.dto.DistrictDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    private final DistrictMapper districtMapper;

    private final CityRepository cityRepository;

    public DistrictDTO createDistrict(DistrictDTO districtDTO) {

        if(districtRepository.existsById(districtDTO.getName()))
            throw PersonalProjectException.badRequest("DistrictNameExisted","District name is already existed");

        if(!cityRepository.existsById(districtDTO.getCityName()))
            throw PersonalProjectException.cityNotFound();

        District district = District.builder()
                .name(districtDTO.getName())
                .city(cityRepository.findById(districtDTO.getCityName()).get())
                .build();

        return districtMapper.toDTO(districtRepository.save(district));
    }

    public List<DistrictDTO> getAllDistrict() {
        List<DistrictDTO> results = new ArrayList<>();

        List<District> districtList = districtRepository.findAll();
        if (districtList.isEmpty())
            throw PersonalProjectException.districtNotFound();

        for (District district : districtList) {
            DistrictDTO dto = DistrictDTO.builder()
                    .name(district.getName())
                    .cityName(district.getCity().getName())
                    .build();
            results.add(dto);
        }

        return results;
    }

//    public DistrictDTO getByDistrictID(String districtName) {
//        District existingDistrict = districtRepository.findById(districtName)
//                .orElseThrow(PersonalProjectException::districtNotFound);
//
//        return DistrictDTO.builder()
//                .districtName(districtName)
//                .cityName(existingDistrict.getCity())
//                .build();
//    }

//    private DistrictDTO updateDistrict(DistrictDTO districtDTO){
//        District existingDistrict = districtRepository.findById(districtDTO.getDistrictName()).
//                orElseThrow(PersonalProjectException::districtNotFound);
//
//        existingDistrict.setDistrictName(districtDTO.getDistrictName());
//        existingDistrict.setCity(districtDTO.getCity());
//
//        return districtMapper.toDTO(districtRepository.save(existingDistrict));
//
//    }

    public void deleteByDistrictID(String districtName) {
        District existingDistrict = districtRepository.findById(districtName).
                orElseThrow(PersonalProjectException::countryNotFound);

        districtRepository.delete(existingDistrict);
    }

}
