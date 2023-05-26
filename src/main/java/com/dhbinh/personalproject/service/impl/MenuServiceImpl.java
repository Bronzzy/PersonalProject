package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Menu;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.MenuMapper;
import com.dhbinh.personalproject.repository.DishCategoryRepository;
import com.dhbinh.personalproject.repository.MenuRepository;
import com.dhbinh.personalproject.repository.RestaurantRepository;
import com.dhbinh.personalproject.service.MenuService;
import com.dhbinh.personalproject.service.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    private final RestaurantRepository restaurantRepository;

    private final DishCategoryRepository dishCategoryRepository;

    private final MenuMapper menuMapper;



    @Override
    public MenuDTO createMenu(MenuDTO menuDTO) {
        Menu menu = Menu.builder()
                .menuID(menuDTO.getMenuID())
                .startingPrice(menuDTO.getStartingPrice())
                .endingPrice(menuDTO.getEndingPrice())
                .ingredients(menuDTO.getIngredients())
                .description(menuDTO.getDescription())
                .dishCategory(dishCategoryRepository.findByDishCategory(menuDTO.getDishCategory()).orElseThrow(PersonalProjectException::dishCategoryNotFound))
                .restaurant(restaurantRepository.findByRestaurantName(menuDTO.getRestaurantName()).orElseThrow(PersonalProjectException::restaurantNotFound))
                .build();

        return menuMapper.toDTO(menuRepository.save(menu));
    }

    @Override
    public List<MenuDTO> getAllMenu() {
        return menuMapper.toDTOs(menuRepository.findAll());
    }

    @Override
    public MenuDTO getMenuByID(Long menuID) {
        log.info("Getting menu {}", menuID);
        Menu existingMenu = menuRepository.findById(menuID)
                .orElseThrow(PersonalProjectException::menuNotFound);
        return menuMapper.toDTO(existingMenu);
    }

    @Override
    public MenuDTO updateMenu(Long menuID, MenuDTO menuDTO) {
        log.info("Updating menu {}", menuID);
        Menu existingMenu = menuRepository.findById(menuID)
                .orElseThrow(PersonalProjectException::menuNotFound);

        existingMenu.setStartingPrice(menuDTO.getStartingPrice());
        existingMenu.setEndingPrice(menuDTO.getEndingPrice());
        existingMenu.setIngredients(menuDTO.getIngredients());
        existingMenu.setDescription(menuDTO.getDescription());
        existingMenu.setDishCategory(dishCategoryRepository.findByDishCategory(menuDTO.getDishCategory()).orElseThrow(PersonalProjectException::dishCategoryNotFound));
        existingMenu.setRestaurant(restaurantRepository.findByRestaurantName(menuDTO.getRestaurantName()).orElseThrow(PersonalProjectException::restaurantNotFound));

        return menuMapper.toDTO(menuRepository.save(existingMenu));

    }

    @Override
    public void deleteMenu(Long menuID) {
        menuRepository.deleteById(menuID);
    }
}
