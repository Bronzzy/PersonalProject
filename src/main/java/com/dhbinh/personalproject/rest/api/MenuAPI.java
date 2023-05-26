package com.dhbinh.personalproject.rest.api;

import com.dhbinh.personalproject.service.dto.MenuDTO;
import com.dhbinh.personalproject.service.dto.MonAnQuanAnDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth/menus")
public interface MenuAPI {

    @PostMapping
    ResponseEntity<MenuDTO> createMenu(@RequestBody MenuDTO menuDTO);

    @GetMapping
    ResponseEntity<List<MenuDTO>> getAllMenu();

    @GetMapping("/bymenuid/{menuid}")
    ResponseEntity<MenuDTO> getMenuByID(@PathVariable("menuID") Long menuID);

    @PutMapping("/update/{menuid}")
    ResponseEntity<MenuDTO> updateMenu(@PathVariable("menuID") Long menuID, @RequestBody MenuDTO menuDTO);

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteMenu(@RequestParam("menuID") Long menuID);

    @GetMapping("/bypriceanddistrict")
    ResponseEntity<List<MonAnQuanAnDTO>> getByPriceAndDistrict(@RequestParam("price") Double price,
                                                         @RequestParam("district") String district);
}
