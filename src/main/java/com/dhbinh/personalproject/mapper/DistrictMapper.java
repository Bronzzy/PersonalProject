package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.District;
import com.dhbinh.personalproject.serviceimpl.dto.DistrictDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DistrictMapper {

    DistrictDTO toDTO(District district);

    List<DistrictDTO> toDTOs(List<District> districts);

    District toEntity(DistrictDTO districtDTO);

    List<District> toEntitys(List<DistrictDTO> districtDTOS);
}
