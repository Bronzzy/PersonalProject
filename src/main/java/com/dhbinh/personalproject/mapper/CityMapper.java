package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.City;
import com.dhbinh.personalproject.service.dto.CityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(target = "countryName", source = "country.countryName")
    CityDTO toDTO(City city);

    List<CityDTO> toDTOs(List<City> cityList);

    City toEntity(CityDTO cityDTO);

    List<City> toEntities(List<CityDTO> cityDTOList);
}
