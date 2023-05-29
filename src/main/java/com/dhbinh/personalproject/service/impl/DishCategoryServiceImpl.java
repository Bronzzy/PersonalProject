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
        if (dishCategoryRepository.existsByType(dishCategoryDTO.getType().trim()))
            throw PersonalProjectException.badRequest("DishCategoryAlreadyExists", "Dish Category already exists");

        DishCategory dishCategory = DishCategory.builder()
                .type(dishCategoryDTO.getType().trim())
                .build();

        return dishCategoryMapper.toDTO(dishCategoryRepository.save(dishCategory));
    }

    @Override
    public List<DishCategoryDTO> getAllDishCategory() {
        return dishCategoryMapper.toDTOs(dishCategoryRepository.findAll());
    }

    @Override
    public DishCategoryDTO getByType(String type) {
        DishCategory existing = dishCategoryRepository.findByType(type.trim())
                .orElseThrow(PersonalProjectException::dishCategoryNotFound);
        return dishCategoryMapper.toDTO(existing);
    }

    @Override
    public DishCategoryDTO updateDishCategory(Long ID, DishCategoryDTO dishCategoryDTO) {
        if (!dishCategoryRepository.existsById(ID))
            throw PersonalProjectException.dishCategoryNotFound();

        DishCategory dishCategory = new DishCategory();

        dishCategory.setType(dishCategoryDTO.getType() == null ? dishCategory.getType() : dishCategoryDTO.getType().trim());

        return dishCategoryMapper.toDTO(dishCategoryRepository.save(dishCategory));
    }

    @Override
    public void deleteByType(String type) {
        if (!dishCategoryRepository.existsByType(type.trim()))
            throw PersonalProjectException.dishCategoryNotFound();
        dishCategoryRepository.deleteByType(type);
    }
}
