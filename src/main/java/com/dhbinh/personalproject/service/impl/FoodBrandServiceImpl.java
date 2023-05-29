package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.FoodBrand;
import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.FoodBrandMapper;
import com.dhbinh.personalproject.mapper.RestaurantMapper;
import com.dhbinh.personalproject.repository.FoodBrandRepository;
import com.dhbinh.personalproject.repository.RestaurantRepository;
import com.dhbinh.personalproject.service.FoodBrandService;
import com.dhbinh.personalproject.service.dto.FoodBrandDTO;
import com.dhbinh.personalproject.service.dto.FoodBrandWithAllRestaurantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodBrandServiceImpl implements FoodBrandService {

    private final FoodBrandRepository foodBrandRepository;

    private final FoodBrandMapper foodBrandMapper;

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;

    public FoodBrandDTO createFoodBrand(FoodBrandDTO foodBrandDTO) {
        if (foodBrandRepository.existsById(foodBrandDTO.getName().trim()))
            throw PersonalProjectException.badRequest("FoodBrandAlreadyExisted", "Foodbrand is already existed");

        FoodBrand foodBrand = FoodBrand.builder()
                .name(foodBrandDTO.getName().trim())
                .build();

        return foodBrandMapper.toDTO(foodBrandRepository.save(foodBrand));
    }

    public List<FoodBrandDTO> getAllFoodBrand() {
        List<FoodBrand> foodBrands = foodBrandRepository.findAll();
        if (foodBrands.isEmpty())
            throw PersonalProjectException.foodBrandNotFound();

        return foodBrandMapper.toDTOs(foodBrands);
    }

    public FoodBrandDTO getByFoodBrandID(String foodBrand) {
        FoodBrand existingFoodBrand = foodBrandRepository.findById(foodBrand.trim()).
                orElseThrow(PersonalProjectException::foodBrandNotFound);

        return foodBrandMapper.toDTO(existingFoodBrand);
    }

    public void deleteByFoodBrandID(String foodBrand) {
        FoodBrand existingFoodBrand = foodBrandRepository.findById(foodBrand.trim())
                .orElseThrow(PersonalProjectException::foodBrandNotFound);

        foodBrandRepository.delete(existingFoodBrand);
    }

    public FoodBrandWithAllRestaurantDTO getFoodBrandWithAllRestaurant(String foodBrand) {

        FoodBrand existingFoodBrand = foodBrandRepository.findById(foodBrand.trim())
                .orElseThrow(PersonalProjectException::foodBrandNotFound);

        List<Restaurant> restaurants = restaurantRepository.findByFoodBrand(existingFoodBrand)
                .orElseThrow(PersonalProjectException::restaurantNotFound);

        return FoodBrandWithAllRestaurantDTO.builder()
                .foodBrand(foodBrandMapper.toDTO(existingFoodBrand))
                .restaurants(restaurantMapper.toDTOs(restaurants))
                .build();
    }
}
