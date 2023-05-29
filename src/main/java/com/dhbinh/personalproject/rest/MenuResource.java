package com.dhbinh.personalproject.rest;

import com.dhbinh.personalproject.rest.api.MenuAPI;
import com.dhbinh.personalproject.service.MenuService;
import com.dhbinh.personalproject.service.dto.MenuDTO;
import com.dhbinh.personalproject.service.dto.RestaurantByRatingAndDistrictDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MenuResource implements MenuAPI {

    private final MenuService menuService;

    @Override
    public ResponseEntity<MenuDTO> createMenu(MenuDTO menuDTO) {
        MenuDTO dto = menuService.createMenu(menuDTO);
        return ResponseEntity.created(URI.create("/api/menus/" + dto.getID())).body(dto);
    }

    @Override
    public ResponseEntity<List<MenuDTO>> getAllMenu() {
        return ResponseEntity.ok(menuService.getAllMenu());
    }

    @Override
    public ResponseEntity<MenuDTO> getMenuByID(Long menuID) {
        return ResponseEntity.ok(menuService.getMenuByID(menuID));
    }

    @Override
    public ResponseEntity<MenuDTO> updateMenu(Long menuID, MenuDTO menuDTO) {
        MenuDTO dto = menuService.updateMenu(menuID, menuDTO);
        return ResponseEntity.created(URI.create("/api/menus/" + dto.getID())).body(dto);
    }

    @Override
    public ResponseEntity<Void> deleteMenu(Long menuID) {
        menuService.deleteMenu(menuID);
        return ResponseEntity.noContent().build();
    }
}
