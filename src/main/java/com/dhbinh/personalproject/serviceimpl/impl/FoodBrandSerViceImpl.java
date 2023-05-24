package com.dhbinh.personalproject.serviceimpl.impl;

import com.dhbinh.personalproject.entity.FoodBrand;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.FoodBrandMapper;
import com.dhbinh.personalproject.repository.FoodBrandRepository;
import com.dhbinh.personalproject.serviceimpl.FoodBrandService;
import com.dhbinh.personalproject.serviceimpl.dto.FoodBrandDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodBrandSerViceImpl implements FoodBrandService {

    private final FoodBrandRepository foodBrandRepository;

    private final FoodBrandMapper foodBrandMapper;
    public List<FoodBrandDTO> getAllFoodBrand(){
        List<FoodBrand> foodBrands = foodBrandRepository.findAll();
        if(foodBrands.isEmpty())
            throw PersonalProjectException.foodBrandNotFound();

        return foodBrandMapper.toDTOs(foodBrands);
    }

    public FoodBrandDTO getByFoodBrandID(String foodBrand){
        FoodBrand existingFoodBrand = foodBrandRepository.findById(foodBrand).
                orElseThrow(PersonalProjectException::foodBrandNotFound);

        return foodBrandMapper.toDTO(existingFoodBrand);
    }

    public void deleteByFoodBrandID(String foodBrand){
        FoodBrand existingFoodBrand = foodBrandRepository.findById(foodBrand)
                .orElseThrow(PersonalProjectException::foodBrandNotFound);

        foodBrandRepository.delete(existingFoodBrand);
    }
}
