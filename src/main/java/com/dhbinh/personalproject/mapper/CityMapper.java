package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.City;
import com.dhbinh.personalproject.serviceimpl.dto.CityDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityDTO toDTO(City city);

    List<CityDTO> toDTOs(List<City> cityList);

    City toEntity(CityDTO cityDTO);

    List<City> toEntitys(List<CityDTO> cityDTOList);
}
