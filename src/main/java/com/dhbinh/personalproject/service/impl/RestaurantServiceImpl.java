package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.RestaurantMapper;
import com.dhbinh.personalproject.repository.DistrictRepository;
import com.dhbinh.personalproject.repository.FoodBrandRepository;
import com.dhbinh.personalproject.repository.RestaurantRepository;
import com.dhbinh.personalproject.service.RestaurantService;
import com.dhbinh.personalproject.service.dto.NumberOfRestaurantByDistrictDTO;
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
import java.util.Optional;

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
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {

        if (restaurantDTO.getName() == null || restaurantDTO.getName().isEmpty() || restaurantDTO.getName().isBlank())
            throw PersonalProjectException.badRequest("NameInvalid", "Name is required");

        if (restaurantDTO.getAddress() == null || restaurantDTO.getAddress().isEmpty() || restaurantDTO.getAddress().isBlank())
            throw PersonalProjectException.badRequest("AddressInvalid", "Address is required");

        String phoneNumber = restaurantDTO.getPhoneNumber();
        if (phoneNumber != null && !phoneNumber.isEmpty() && !phoneNumber.isBlank()) {
            if (!isValidPhoneNumber(phoneNumber)) {
                throw PersonalProjectException.badRequest("PhoneNumberInvalid", "Phone number can only contain numbers");
            }
        }

        if (restaurantDTO.getOpenHour() == null)
            throw PersonalProjectException.badRequest("OpenHourInvalid", "Open hour is required");

        if (restaurantDTO.getClosingHour() == null)
            throw PersonalProjectException.badRequest("ClosingHourInvalid", "Closing hour is required");

        if (restaurantDTO.getOpenHour().isAfter(restaurantDTO.getClosingHour()))
            throw PersonalProjectException.badRequest("InvalidHour", "Open hour cant be after closing hour");

        if (restaurantDTO.getDistrictName() == null || restaurantDTO.getDistrictName().isEmpty() || restaurantDTO.getDistrictName().isBlank())
            throw PersonalProjectException.badRequest("DistrictNameInvalid", "District name is required");

        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDTO.getName().trim())
                .address(restaurantDTO.getAddress().trim())
                .description(restaurantDTO.getDescription())
                .phoneNumber(restaurantDTO.getPhoneNumber())
                .openHour(restaurantDTO.getOpenHour())
                .closingHour(restaurantDTO.getClosingHour())
                .district(districtRepository.findById(restaurantDTO.getDistrictName().trim())
                        .orElseThrow(PersonalProjectException::districtNotFound))
                .foodBrand(restaurantDTO.getFoodBrand() == null ? null :
                        foodBrandRepository.findById(restaurantDTO.getFoodBrand())
                                .orElseThrow(PersonalProjectException::foodBrandNotFound))
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

        if (restaurantDTO.getName() == null || restaurantDTO.getName().isEmpty() || restaurantDTO.getName().isBlank())
            throw PersonalProjectException.badRequest("NameInvalid", "Name is required");

        if (restaurantDTO.getAddress() == null || restaurantDTO.getAddress().isEmpty() || restaurantDTO.getAddress().isBlank())
            throw PersonalProjectException.badRequest("AddressInvalid", "Address is required");

        String phoneNumber = restaurantDTO.getPhoneNumber();
        if (phoneNumber != null) {
            if (!isValidPhoneNumber(phoneNumber)) {
                throw PersonalProjectException.badRequest("PhoneNumberInvalid", "Phone number can only contain numbers");
            }
        }

        if (restaurantDTO.getOpenHour() == null)
            throw PersonalProjectException.badRequest("OpenHourInvalid", "Open hour is required");

        if (restaurantDTO.getClosingHour() == null)
            throw PersonalProjectException.badRequest("ClosingHourInvalid", "Closing hour is required");

        if (restaurantDTO.getOpenHour().isAfter(restaurantDTO.getClosingHour()))
            throw PersonalProjectException.badRequest("InvalidHour", "Open hour cant be after closing hour");

        if (restaurantDTO.getDistrictName() == null || restaurantDTO.getDistrictName().isEmpty() || restaurantDTO.getDistrictName().isBlank())
            throw PersonalProjectException.badRequest("DistrictNameInvalid", "District name is required");

        existingRestaurant.setName(restaurantDTO.getName().trim());
        existingRestaurant.setAddress(restaurantDTO.getAddress().trim());
        existingRestaurant.setDescription(restaurantDTO.getDescription());
        existingRestaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        existingRestaurant.setOpenHour(restaurantDTO.getOpenHour());
        existingRestaurant.setClosingHour(restaurantDTO.getClosingHour());
        existingRestaurant.setPicture(restaurantDTO.getPicture());
        existingRestaurant.setDistrict(districtRepository.findById(restaurantDTO.getDistrictName().trim())
                .orElseThrow(PersonalProjectException::districtNotFound));
        existingRestaurant.setFoodBrand(restaurantDTO.getFoodBrand() == null ? null :
                foodBrandRepository.findById(restaurantDTO.getFoodBrand())
                        .orElseThrow(PersonalProjectException::foodBrandNotFound));

        return restaurantMapper.toDTO(restaurantRepository.save(existingRestaurant));
    }

    public void deleteByRestaurantName(String restaurantName) {

        Restaurant restaurant = restaurantRepository.findByName(restaurantName.trim())
                .orElseThrow(PersonalProjectException::restaurantNotFound);

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<RestaurantDTO> getRestaurantByDishCategory(String dishCategory, String districtName, Pageable
            pageable) {

        dishCategory = "%" + dishCategory + "%";
        districtName = "%" + districtName + "%";

        List<RestaurantDTO> dtoList = new ArrayList<>();

        List<Restaurant> restaurantList = restaurantRepository.getRestaurantByDishCategory(dishCategory, districtName, pageable);

        for (Restaurant res : restaurantList) {
            RestaurantDTO dto = RestaurantDTO.builder()
                    .ID(res.getID())
                    .name(res.getName().trim())
                    .address(res.getAddress().trim())
                    .description(res.getDescription().trim())
                    .phoneNumber(res.getPhoneNumber().trim())
                    .openHour(res.getOpenHour())
                    .closingHour(res.getClosingHour())
                    .picture(res.getPicture())
                    .districtName(res.getDistrict().getName().trim())
                    .foodBrand(res.getFoodBrand() == null ? null : res.getFoodBrand().getName().trim())
                    .build();

            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<NumberOfRestaurantByDistrictDTO> getNumberOfRestaurantByDistrict() {
        return restaurantRepository.getNumberOfRestaurantByDistrict();
    }

    @Override
    public List<RestaurantDTO> getByRatingOpenHourAndClosingHour(double rating, String openHour, String
            closingHour) {

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
                    .ID(restaurant.getID())
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

    @Override
    public List<RestaurantDTO> getRestaurantByPrice(double startingPrice, double endingPrice) {
        return restaurantMapper.toDTOs(restaurantRepository.getRestaurantByPrice(startingPrice,endingPrice));
    }

    @Override
    public Restaurant findByName(String restaurantName) {
        return restaurantRepository.findByName(restaurantName.trim())
                .orElseThrow(PersonalProjectException::restaurantNotFound);
    }

    private boolean isValidTimeFormat(String timeString) {
        String pattern = "^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$";
        return timeString.matches(pattern);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String pattern = "^\\d+$";
        return phoneNumber.matches(pattern);
    }
}
