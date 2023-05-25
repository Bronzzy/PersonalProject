package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.Country;
import com.dhbinh.personalproject.service.dto.CountryDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO toDTO(Country country);

    Country toEntity(CountryDTO countryDTO);

    List<CountryDTO> toDTOs(List<Country> countries);

    List<Country> toEntities(List<CountryDTO> countryDTOS);
}
