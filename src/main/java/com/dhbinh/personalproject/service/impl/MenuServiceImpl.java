package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Menu;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.MenuMapper;
import com.dhbinh.personalproject.repository.DishCategoryRepository;
import com.dhbinh.personalproject.repository.MenuRepository;
import com.dhbinh.personalproject.repository.RestaurantRepository;
import com.dhbinh.personalproject.service.MenuService;
import com.dhbinh.personalproject.service.dto.MenuDTO;
import com.dhbinh.personalproject.service.dto.MonAnQuanAnDTO;
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
                .ingredients(menuDTO.getIngredients().trim())
                .description(menuDTO.getDescription().trim())
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
        existingMenu.setIngredients(menuDTO.getIngredients().trim());
        existingMenu.setDescription(menuDTO.getDescription().trim());
        existingMenu.setDishCategory(dishCategoryRepository.findByType(menuDTO.getDishCategory().trim())
                .orElseThrow(PersonalProjectException::dishCategoryNotFound));
        existingMenu.setRestaurant(restaurantRepository.findByName(menuDTO.getRestaurantName().trim())
                .orElseThrow(PersonalProjectException::restaurantNotFound));

        return menuMapper.toDTO(menuRepository.save(existingMenu));

    }

    @Override
    public void deleteMenu(Long menuID) {
        menuRepository.deleteById(menuID);
    }

    public List<MonAnQuanAnDTO> getByPriceAndDistrict(Double startingPrice, String district) {
        List<MonAnQuanAnDTO> result = new ArrayList<>();

        List<Menu> rawList = menuRepository.findAll().stream()
                .filter(m -> m.getStartingPrice() < startingPrice)
                .filter(m -> "Quan 1".equals(m.getRestaurant().getDistrict().getName()))
                .collect(Collectors.toList());
        for (Menu menu : rawList) {
            MonAnQuanAnDTO dto = MonAnQuanAnDTO.builder()
                    .dishCategory(menu.getDishCategory().getType())
                    .restaurantName(menu.getRestaurant().getName())
                    .address(menu.getRestaurant().getAddress())
                    .openHour(menu.getRestaurant().getOpenHour())
                    .closeHour(menu.getRestaurant().getClosingHour())
                    .build();
            result.add(dto);
        }
        return result;
    }


    private static void isMenuDTOValid(MenuDTO menuDTO){
        if(menuDTO.getRestaurantName().isEmpty() || menuDTO.getRestaurantName().isBlank() || menuDTO.getRestaurantName() == null)
            throw PersonalProjectException.badRequest("InvalidRestaurantName",
                    "Restaurant name must not be empty or null");

        if(menuDTO.getDishCategory().isEmpty() || menuDTO.getDishCategory().isBlank() || menuDTO.getDishCategory() == null)
            throw PersonalProjectException.badRequest("InvalidDishCategory",
                    "Dish category must not be empty or null");

        if(menuDTO.getDescription().isEmpty() || menuDTO.getDescription().isBlank() || menuDTO.getDescription() == null)
            throw PersonalProjectException.badRequest("InvalidDescription",
                    "Description must not be empty or null");
        if(menuDTO.getStartingPrice() == null || menuDTO.getEndingPrice() == null)
            throw PersonalProjectException.badRequest("InvalidPrice","Price can't be null");

        if(menuDTO.getStartingPrice().equals(menuDTO.getEndingPrice()) || menuDTO.getStartingPrice() == menuDTO.getEndingPrice())
            throw PersonalProjectException.badRequest("InvalidPrice",
                    "Starting price and ending price must be different");

        if(menuDTO.getStartingPrice() > menuDTO.getEndingPrice())
            throw PersonalProjectException.badRequest("InvalidPrice",
                    "Starting price must be less than ending price");

        if(menuDTO.getEndingPrice() < menuDTO.getStartingPrice())
            throw PersonalProjectException.badRequest("InvalidPrice",
                    "Ending price must be greater than starting price");

        if(menuDTO.getStartingPrice() < 0)
            throw PersonalProjectException.badRequest("InvalidPrice",
                    "Starting price must be greater than 0");

        if(menuDTO.getEndingPrice() < 0)
            throw PersonalProjectException.badRequest("InvalidPrice",
                    "Ending price must be greater than 0");


        if(menuDTO.getIngredients().isEmpty() || menuDTO.getIngredients().isBlank() || menuDTO.getIngredients() == null)
            throw PersonalProjectException.badRequest("InvalidIngredients",
                    "Ingredients must not be empty or null");
    }
}
