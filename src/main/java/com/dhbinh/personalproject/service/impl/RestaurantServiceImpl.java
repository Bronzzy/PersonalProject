package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.RestaurantMapper;
import com.dhbinh.personalproject.repository.DistrictRepository;
import com.dhbinh.personalproject.repository.FoodBrandRepository;
import com.dhbinh.personalproject.repository.RestaurantRepository;
import com.dhbinh.personalproject.service.RestaurantService;
import com.dhbinh.personalproject.service.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;

    private final DistrictRepository districtRepository;

    private final FoodBrandRepository foodBrandRepository;

    @Override
    public RestaurantDTO createRestaurant(@Valid RestaurantDTO restaurantDTO) {
        log.info("restaurantDTO {}", restaurantDTO);
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDTO.getName())
                .address(restaurantDTO.getAddress())
                .description(restaurantDTO.getDescription())
                .phoneNumber(restaurantDTO.getPhoneNumber())
                .openHour(restaurantDTO.getOpenHour())
                .closingHour(restaurantDTO.getClosingHour())
                .district(districtRepository.findById(restaurantDTO.getDistrictName())
                        .orElseThrow(PersonalProjectException::districtNotFound))
                .foodBrand(foodBrandRepository.findById(restaurantDTO.getFoodBrand()).isEmpty() ? null :
                        foodBrandRepository.findById(restaurantDTO.getFoodBrand()).get())
                .build();

        return restaurantMapper.toDTO(restaurantRepository.save(restaurant));
    }

    public List<RestaurantDTO> getAllRestaurant() {

        List<RestaurantDTO> resultList = new ArrayList<>();

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        if (restaurantList.isEmpty()) {
            throw PersonalProjectException.restaurantNotFound();
        }

        for (Restaurant res : restaurantList) {
            RestaurantDTO dto = RestaurantDTO.builder()
                    .ID(res.getID())
                    .name(res.getName())
                    .address(res.getAddress())
                    .phoneNumber(res.getPhoneNumber())
                    .openHour(res.getOpenHour())
                    .closingHour(res.getClosingHour())
                    .picture(res.getPicture())
                    .districtName(res.getDistrict().getName())
                    .foodBrand(res.getFoodBrand() == null ? null : res.getFoodBrand().getName())
                    .build();

            resultList.add(dto);
        }
        return resultList;
    }

    public RestaurantDTO getByRestaurantName(String restaurantName) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName)
                .orElseThrow(PersonalProjectException::restaurantNotFound);

        return restaurantMapper.toDTO(restaurant);
    }

    @Override
    public RestaurantDTO updateRestaurant(Long restaurantID, RestaurantDTO restaurantDTO) {
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantID)
                .orElseThrow(PersonalProjectException::restaurantNotFound);

        existingRestaurant.setName(restaurantDTO.getName());
        existingRestaurant.setAddress(restaurantDTO.getAddress());
        existingRestaurant.setDescription(restaurantDTO.getDescription());
        existingRestaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        existingRestaurant.setOpenHour(restaurantDTO.getOpenHour());
        existingRestaurant.setClosingHour(restaurantDTO.getClosingHour());
        existingRestaurant.setPicture(restaurantDTO.getPicture());
        existingRestaurant.setDistrict(districtRepository.findById(restaurantDTO.getDistrictName())
                .orElseThrow(PersonalProjectException::districtNotFound));
        existingRestaurant.setFoodBrand(foodBrandRepository.findById(restaurantDTO.getFoodBrand()).isEmpty() ? null :
                foodBrandRepository.findById(restaurantDTO.getFoodBrand()).get());

        return restaurantMapper.toDTO(restaurantRepository.save(existingRestaurant));
    }

    public void deleteByRestaurantName(String restaurantName) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName)
                .orElseThrow(PersonalProjectException::restaurantNotFound);

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<RestaurantDTO> getRestaurantByDishCategory(String dishCategory, String districtName, Pageable pageable) {

        dishCategory = "%" + dishCategory + "%";
        districtName = "%" + districtName + "%";

        List<RestaurantDTO> dtoList = new ArrayList<>();

        List<Restaurant> restaurantList = restaurantRepository.getRestaurantByDishCategory(dishCategory, districtName, pageable);

        for (Restaurant res : restaurantList) {
            RestaurantDTO dto = RestaurantDTO.builder()
                    .name(res.getName())
                    .address(res.getAddress())
                    .description(res.getDescription())
                    .phoneNumber(res.getPhoneNumber())
                    .openHour(res.getOpenHour())
                    .closingHour(res.getClosingHour())
                    .picture(res.getPicture())
                    .districtName(res.getDistrict().getName())
                    .foodBrand(res.getFoodBrand() == null ? null : res.getFoodBrand().getName())
                    .build();

            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<Object[]> getNumberOfRestaurantByDistrict() {
        return restaurantRepository.getNumberOfRestaurantByDistrict();
    }

    @Override
    public List<RestaurantDTO> getByRatingOpenHourAndClosingHour(double rating, String openHour, String closingHour) {
        if (!isValidTimeFormat(openHour))
            throw PersonalProjectException.badRequest("WrongTimeFormat", "Time format must be hh:mm:ss");

        if (!isValidTimeFormat(closingHour))
            throw PersonalProjectException.badRequest("WrongTimeFormat", "Time format must be hh:mm:ss");

        LocalTime open = LocalTime.parse(openHour);
        LocalTime closing = LocalTime.parse(closingHour);

        List<RestaurantDTO> results = new ArrayList<>();

        List<Restaurant> raw = restaurantRepository.getByRatingOpenHourAndClosingHour(rating, open, closing);
        for (Restaurant restaurant : raw) {
            RestaurantDTO dto = RestaurantDTO.builder()
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .description(restaurant.getDescription())
                    .phoneNumber(restaurant.getPhoneNumber())
                    .openHour(restaurant.getOpenHour())
                    .closingHour(restaurant.getClosingHour())
                    .picture(restaurant.getPicture())
                    .districtName(restaurant.getDistrict().getName())
                    .foodBrand(restaurant.getFoodBrand() == null ? null : restaurant.getFoodBrand().getName())
                    .build();

            results.add(dto);
        }
        return results;
    }

    public boolean isValidTimeFormat(String timeString) {
        String pattern = "^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$";
        return timeString.matches(pattern);
    }
}
