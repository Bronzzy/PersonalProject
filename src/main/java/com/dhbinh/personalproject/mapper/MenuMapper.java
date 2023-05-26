package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.Menu;
import com.dhbinh.personalproject.service.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(source = "dishCategory.type", target = "dishCategory")
    @Mapping(source = "restaurant.name", target = "restaurantName")
    MenuDTO toDTO(Menu menu);
    List<MenuDTO> toDTOs(List<Menu> menus);
}
