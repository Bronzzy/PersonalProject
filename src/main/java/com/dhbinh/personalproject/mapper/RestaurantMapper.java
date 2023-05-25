package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.service.dto.RestaurantDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantDTO toDTO(Restaurant restaurant);

    List<RestaurantDTO> toDTOs(List<Restaurant> restaurants);

    Restaurant toEntity(RestaurantDTO restaurantDTO);

    List<Restaurant> toEntities(List<RestaurantDTO> restaurantDTOS);
}
