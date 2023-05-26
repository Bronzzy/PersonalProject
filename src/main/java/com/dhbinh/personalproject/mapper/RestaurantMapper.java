package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.service.dto.RestaurantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "districtName", source = "district.name")
    @Mapping(target = "foodBrand", source = "foodBrand.name")
    RestaurantDTO toDTO(Restaurant restaurant);

    List<RestaurantDTO> toDTOs(List<Restaurant> restaurants);

}
