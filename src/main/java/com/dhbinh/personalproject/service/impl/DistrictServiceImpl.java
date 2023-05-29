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

        if (districtRepository.existsById(districtDTO.getName().trim()))
            throw PersonalProjectException.badRequest("DistrictNameExisted", "District name is already existed");

        District district = District.builder()
                .name(districtDTO.getName().trim())
                .city(cityRepository.findById(districtDTO.getCityName().trim())
                        .orElseThrow(PersonalProjectException::cityNotFound))
                .build();

        districtRepository.save(district);
        return districtMapper.toDTO(district);
    }

    public List<DistrictDTO> getAllDistrict() {

        List<District> districtList = districtRepository.findAll();
        if (districtList.isEmpty())
            throw PersonalProjectException.districtNotFound();

        return districtMapper.toDTOs(districtList);
    }

    public DistrictDTO getByDistrictID(String districtName) {
        District existingDistrict = districtRepository.findById(districtName)
                .orElseThrow(PersonalProjectException::districtNotFound);

        return districtMapper.toDTO(existingDistrict);
    }

    public void deleteByDistrictID(String districtName) {
        District existingDistrict = districtRepository.findById(districtName.trim()).
                orElseThrow(PersonalProjectException::countryNotFound);

        districtRepository.delete(existingDistrict);
    }

}
