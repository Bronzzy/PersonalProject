package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.FoodBrand;
import com.dhbinh.personalproject.service.dto.FoodBrandDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodBrandMapper {

    FoodBrandDTO toDTO(FoodBrand foodBrand);

    List<FoodBrandDTO> toDTOs(List<FoodBrand> foodBrands);

    FoodBrand toEntity(FoodBrandDTO foodBrandDTO);

    List<FoodBrand> toEntities(List<FoodBrandDTO> foodBrandDTOS);
}
