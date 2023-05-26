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
        if (dishCategoryRepository.existsByType(dishCategoryDTO.getType()))
            throw PersonalProjectException.badRequest("DishCategoryAlreadyExists", "Dish Category already exists");

        DishCategory dishCategory = DishCategory.builder()
                .ID(dishCategoryDTO.getID())
                .type(dishCategoryDTO.getType())
                .build();

        return dishCategoryMapper.toDTO(dishCategoryRepository.save(dishCategory));
    }

    @Override
    public List<DishCategoryDTO> getAllDishCategory() {
        return dishCategoryMapper.toDTOs(dishCategoryRepository.findAll());
    }

    @Override
    public DishCategoryDTO getByType(String type) {
        DishCategory existing = dishCategoryRepository.findByType(type)
                .orElseThrow(PersonalProjectException::dishCategoryNotFound);
        return dishCategoryMapper.toDTO(existing);
    }

    @Override
    public DishCategoryDTO updateDishCategory(Long ID, DishCategoryDTO dishCategoryDTO) {
        if (!dishCategoryRepository.existsById(ID))
            throw PersonalProjectException.dishCategoryNotFound();

        DishCategory dishCategory = DishCategory.builder()
                .ID(ID)
                .type(dishCategoryDTO.getType())
                .build();

        return dishCategoryMapper.toDTO(dishCategoryRepository.save(dishCategory));
    }

    @Override
    public void deleteByType(String type) {
        dishCategoryRepository.deleteByType(type);
    }
}
