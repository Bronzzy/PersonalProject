package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.MenuDTO;
import com.dhbinh.personalproject.service.dto.RestaurantByRatingAndDistrictDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/menus")
public interface MenuAPI {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<MenuDTO> createMenu(@RequestBody MenuDTO menuDTO);

    @GetMapping
    ResponseEntity<List<MenuDTO>> getAllMenu();

    @GetMapping("/bymenuid/{menuid}")
    ResponseEntity<MenuDTO> getMenuByID(@PathVariable("menuid") Long menuID);

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{menuid}")
    ResponseEntity<MenuDTO> updateMenu(@PathVariable("menuid") Long menuID, @RequestBody MenuDTO menuDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteMenu(@RequestParam("menuID") Long menuID);

//    @GetMapping("/bypriceanddistrict")
//    ResponseEntity<List<RestaurantByRatingAndDistrictDTO>> getByPriceAndDistrict(@RequestParam("price") Double price,
//                                                                                 @RequestParam("district") String district);
}
