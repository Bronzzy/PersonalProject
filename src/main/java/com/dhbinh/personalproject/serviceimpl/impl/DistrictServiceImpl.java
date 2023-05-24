package com.dhbinh.personalproject.serviceimpl.impl;


import com.dhbinh.personalproject.entity.District;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.DistrictMapper;
import com.dhbinh.personalproject.repository.DistrictRepository;
import com.dhbinh.personalproject.serviceimpl.DistrictService;
import com.dhbinh.personalproject.serviceimpl.dto.DistrictDTO;
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

//    public DistrictDTO createDistrict(DistrictDTO districtDTO) {
//
//        Optional<District> existingDistrict = districtRepository.findById(districtDTO.getDistrictName());
//        if(existingDistrict.isEmpty()){
//            throw PersonalProjectException.badRequest("DistrictExisted","District name is already existed");
//        }
//
//        District district = District.builder()
//                .districtName(districtDTO.getDistrictName())
//                .city(districtDTO.getCity())
//                .build();
//
//        return districtMapper.toDTO(districtRepository.save(district));
//    }

    public List<DistrictDTO> getAllDistrict() {
        List<DistrictDTO> results = new ArrayList<>();

        List<District> districtList = districtRepository.findAll();
        if (districtList.isEmpty())
            throw PersonalProjectException.districtNotFound();

        for (District district : districtList) {
            DistrictDTO dto = DistrictDTO.builder()
                    .districtName(district.getDistrictName())
                    .cityName(district.getCity().getCityName())
                    .build();
            results.add(dto);
        }
        return results;
    }

    public DistrictDTO getByDistrictID(String districtName) {
        District existingDistrict = districtRepository.findById(districtName)
                .orElseThrow(PersonalProjectException::districtNotFound);

        return DistrictDTO.builder()
                .districtName(districtName)
                .cityName(existingDistrict.getCity().getCityName())
                .build();
    }

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
