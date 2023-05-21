package com.dhbinh.personalproject.serviceimpl;

import com.dhbinh.personalproject.PersonalprojectApplication;
import com.dhbinh.personalproject.entity.District;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.DistrictMapper;
import com.dhbinh.personalproject.repository.DistrictRepository;
import com.dhbinh.personalproject.serviceimpl.dto.DistrictDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.PersistentObjectException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DistrictServiceImpl {

    private final DistrictRepository districtRepository;

    private final DistrictMapper districtMapper;

    private DistrictDTO createDistrict(DistrictDTO districtDTO) {

        Optional<District> existingDistrict = districtRepository.findById(districtDTO.getDistrictName());
        if(existingDistrict.isEmpty()){
            throw PersonalProjectException.badRequest("DistrictExisted","District name is already existed");
        }

        District district = District.builder()
                .districtName(districtDTO.getDistrictName())
                .city(districtDTO.getCity())
                .build();

        return districtMapper.toDTO(districtRepository.save(district));
    }

    private List<DistrictDTO> getAllDistrict(){
        List<District> districtList = districtRepository.findAll();
        if(districtList.isEmpty())
            throw PersonalProjectException.districtNotFound();

        return districtMapper.toDTOs(districtList);
    }

    private DistrictDTO getByDistrictID(String districtName){
        Optional<District> existingDistrict = districtRepository.findById(districtName);
        if(existingDistrict.isEmpty()){
            throw PersonalProjectException.districtNotFound();
        }

        return districtMapper.toDTO(existingDistrict.get());
    }

    private DistrictDTO updateDistrict(DistrictDTO districtDTO){
        District existingDistrict = districtRepository.findById(districtDTO.getDistrictName()).
                orElseThrow(PersonalProjectException::districtNotFound);

        existingDistrict.setDistrictName(districtDTO.getDistrictName());
        existingDistrict.setCity(districtDTO.getCity());

        return districtMapper.toDTO(districtRepository.save(existingDistrict));

    }
}
