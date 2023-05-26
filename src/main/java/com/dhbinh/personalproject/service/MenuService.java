package com.dhbinh.personalproject.service;

import com.dhbinh.personalproject.service.dto.MenuDTO;

import java.util.List;

public interface MenuService {

    MenuDTO createMenu(MenuDTO menuDTO);

    List<MenuDTO> getAllMenu();

    MenuDTO getMenuByID(Long menuID);

    MenuDTO updateMenu(Long menuID, MenuDTO menuDTO);

    void deleteMenu(Long menuID);
}
