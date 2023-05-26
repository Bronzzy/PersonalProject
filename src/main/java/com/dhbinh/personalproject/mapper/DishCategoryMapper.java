package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.DishCategory;
import com.dhbinh.personalproject.service.dto.DishCategoryDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishCategoryMapper {

    DishCategoryDTO toDTO(DishCategory dishCategory);

    List<DishCategoryDTO> toDTOs(List<DishCategory> dishCategories);
}
