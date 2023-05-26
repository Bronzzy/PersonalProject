package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.DishCategory;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.DishCategoryMapper;
import com.dhbinh.personalproject.repository.DishCategoryRepository;
import com.dhbinh.personalproject.service.DishCategoryService;
import com.dhbinh.personalproject.service.dto.DishCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DishCategoryServiceImpl implements DishCategoryService {

    private final DishCategoryRepository dishCategoryRepository;

    private final DishCategoryMapper dishCategoryMapper;
    @Override
    public DishCategoryDTO createDishCategory(DishCategoryDTO dishCategoryDTO) {
        if(dishCategoryRepository.existsByDishCategory(dishCategoryDTO.getDishCategory()))
            throw PersonalProjectException.badRequest("DishCategoryAlreadyExists","Dish Category already exists");

        DishCategory dishCategory = DishCategory.builder()
                .dishCategoryID(dishCategoryDTO.getDishCategoryID())
                .dishCategory(dishCategoryDTO.getDishCategory())
                .build();

        return dishCategoryMapper.toDTO(dishCategoryRepository.save(dishCategory));
    }

    @Override
    public List<DishCategoryDTO> getAllDishCategory() {
        return dishCategoryMapper.toDTOs(dishCategoryRepository.findAll());
    }

    @Override
    public DishCategoryDTO getByDishCategory(String dishCategory) {
        DishCategory existing = dishCategoryRepository.findByDishCategory(dishCategory)
                .orElseThrow(PersonalProjectException::dishCategoryNotFound);
        return dishCategoryMapper.toDTO(existing);
    }

    @Override
    public DishCategoryDTO updateDishCategory(Long dishCategoryID, DishCategoryDTO dishCategoryDTO) {
        if(!dishCategoryRepository.existsById(dishCategoryID))
            throw PersonalProjectException.dishCategoryNotFound();

        DishCategory dishCategory = DishCategory.builder()
                .dishCategoryID(dishCategoryID)
                .dishCategory(dishCategoryDTO.getDishCategory())
                .build();

        return dishCategoryMapper.toDTO(dishCategoryRepository.save(dishCategory));
    }

    @Override
    public void deleteByDishCategory(String dishCategory) {
        dishCategoryRepository.deleteByDishCategory(dishCategory);
    }
}
