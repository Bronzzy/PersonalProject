package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.Country;
import com.dhbinh.personalproject.serviceimpl.dto.CountryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO toDTO(Country country);

    Country toEntity(CountryDTO countryDTO);

    List<CountryDTO> toDTOs(List<Country> countries);

    List<Country> toEntitys(List<CountryDTO> countryDTOS);
}
