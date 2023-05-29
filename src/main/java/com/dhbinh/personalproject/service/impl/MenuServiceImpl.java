package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Menu;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.MenuMapper;
import com.dhbinh.personalproject.repository.DishCategoryRepository;
import com.dhbinh.personalproject.repository.MenuRepository;
import com.dhbinh.personalproject.repository.RestaurantRepository;
import com.dhbinh.personalproject.service.MenuService;
import com.dhbinh.personalproject.service.dto.MenuDTO;
import com.dhbinh.personalproject.service.dto.RestaurantByRatingAndDistrictDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        log.info("Creating menu {}", menuDTO);

        isMenuDTOValid(menuDTO);

        Menu menu = Menu.builder()
                .startingPrice(menuDTO.getStartingPrice())
                .endingPrice(menuDTO.getEndingPrice())
                .ingredients(menuDTO.getIngredients())
                .description(menuDTO.getDescription())
                .dishCategory(dishCategoryRepository.findByType(menuDTO.getDishCategory().trim())
                        .orElseThrow(PersonalProjectException::dishCategoryNotFound))
                .restaurant(restaurantRepository.findByName(menuDTO.getRestaurantName().trim())
                        .orElseThrow(PersonalProjectException::restaurantNotFound))
                .build();

        return menuMapper.toDTO(menuRepository.save(menu));
    }

    @Override
    public List<MenuDTO> getAllMenu() {
        return menuMapper.toDTOs(menuRepository.findAll());
    }

    @Override
    public MenuDTO getMenuByID(Long menuID) {

        Menu existingMenu = menuRepository.findById(menuID)
                .orElseThrow(PersonalProjectException::menuNotFound);
        return menuMapper.toDTO(existingMenu);
    }

    @Override
    public MenuDTO updateMenu(Long menuID, MenuDTO menuDTO) {

        Menu existingMenu = menuRepository.findById(menuID)
                .orElseThrow(PersonalProjectException::menuNotFound);

        if (menuDTO.getEndingPrice() != null && menuDTO.getStartingPrice() != null) {
            if (menuDTO.getEndingPrice() < menuDTO.getStartingPrice())
                throw PersonalProjectException.badRequest("InvalidPrice",
                        "Ending price must be greater than starting price");
        }

        if (menuDTO.getEndingPrice() != null) {
            if (menuDTO.getEndingPrice().compareTo(existingMenu.getStartingPrice()) > 0 && menuDTO.getEndingPrice().compareTo(existingMenu.getStartingPrice()) < 0)
                throw PersonalProjectException.badRequest("InvalidPrice", "Ending price must be less than starting price");
        }

        existingMenu.setStartingPrice(menuDTO.getStartingPrice() == null ? existingMenu.getStartingPrice() : menuDTO.getStartingPrice());
        existingMenu.setEndingPrice(menuDTO.getEndingPrice() == null ? existingMenu.getEndingPrice() : menuDTO.getEndingPrice());
        existingMenu.setIngredients(menuDTO.getIngredients() == null ? existingMenu.getIngredients() : menuDTO.getIngredients().trim());
        existingMenu.setDescription(menuDTO.getDescription() == null ? existingMenu.getDescription() : menuDTO.getDescription().trim());
        existingMenu.setDishCategory(menuDTO.getDishCategory() == null ? existingMenu.getDishCategory() : dishCategoryRepository.findByType(menuDTO.getDishCategory())
                .orElseThrow(PersonalProjectException::dishCategoryNotFound));
        existingMenu.setRestaurant(menuDTO.getRestaurantName() == null ? existingMenu.getRestaurant() : restaurantRepository.findByName(menuDTO.getRestaurantName())
                .orElseThrow(PersonalProjectException::restaurantNotFound));

        return menuMapper.toDTO(menuRepository.save(existingMenu));

    }

    @Override
    public void deleteMenu(Long menuID) {
        menuRepository.deleteById(menuID);
    }






    private static void isMenuDTOValid(MenuDTO menuDTO) {
        if (menuDTO.getRestaurantName().isEmpty() || menuDTO.getRestaurantName().isBlank() || menuDTO.getRestaurantName() == null)
            throw PersonalProjectException.badRequest("InvalidRestaurantName",
                    "Restaurant name must not be empty or null");

        if (menuDTO.getDishCategory().isEmpty() || menuDTO.getDishCategory().isBlank() || menuDTO.getDishCategory() == null)
            throw PersonalProjectException.badRequest("InvalidDishCategory",
                    "Dish category must not be empty or null");

        if (menuDTO.getDescription().isEmpty() || menuDTO.getDescription().isBlank() || menuDTO.getDescription() == null)
            throw PersonalProjectException.badRequest("InvalidDescription",
                    "Description must not be empty or null");
        if (menuDTO.getStartingPrice() == null || menuDTO.getEndingPrice() == null)
            throw PersonalProjectException.badRequest("InvalidPrice", "Price can't be null");

        if (menuDTO.getStartingPrice().equals(menuDTO.getEndingPrice()) || menuDTO.getStartingPrice() == menuDTO.getEndingPrice())
            throw PersonalProjectException.badRequest("InvalidPrice",
                    "Starting price and ending price must be different");

        if (menuDTO.getEndingPrice() < menuDTO.getStartingPrice())
            throw PersonalProjectException.badRequest("InvalidPrice",
                    "Ending price must be greater than starting price");

        if (menuDTO.getStartingPrice() < 0)
            throw PersonalProjectException.badRequest("InvalidPrice",
                    "Starting price must be greater than 0");

        if (menuDTO.getEndingPrice() < 0)
            throw PersonalProjectException.badRequest("InvalidPrice",
                    "Ending price must be greater than 0");

        if (menuDTO.getIngredients().isEmpty() || menuDTO.getIngredients().isBlank() || menuDTO.getIngredients() == null)
            throw PersonalProjectException.badRequest("InvalidIngredients",
                    "Ingredients must not be empty or null");
    }
}
