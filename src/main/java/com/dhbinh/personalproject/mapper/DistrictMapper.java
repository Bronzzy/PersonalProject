package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.District;
import com.dhbinh.personalproject.service.dto.DistrictDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DistrictMapper {

    @Mapping(target = "cityName", source = "city.name")
    DistrictDTO toDTO(District district);

    List<DistrictDTO> toDTOs(List<District> districts);

    District toEntity(DistrictDTO districtDTO);

    List<District> toEntitys(List<DistrictDTO> districtDTOS);
}
