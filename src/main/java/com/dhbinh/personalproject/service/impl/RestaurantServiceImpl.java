package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.RestaurantMapper;
import com.dhbinh.personalproject.repository.RestaurantRepository;
import com.dhbinh.personalproject.service.RestaurantService;
import com.dhbinh.personalproject.service.dto.CustomRestaurantStatisticDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;


    public List<CustomRestaurantStatisticDTO> getAllRestaurant() {
        List<CustomRestaurantStatisticDTO> resultList = new ArrayList<>();

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        if (restaurantList.isEmpty()) {
            throw PersonalProjectException.restaurantNotFound();
        }

        for (Restaurant res : restaurantList) {
            CustomRestaurantStatisticDTO customRestaurantStatisticDTO = new CustomRestaurantStatisticDTO();

            customRestaurantStatisticDTO = CustomRestaurantStatisticDTO.builder()
                    .restaurantName(res.getRestaurantName())
                    .restaurantAddress(res.getRestaurantAddress())
                    .telephoneNumber(res.getTelephoneNumber())
                    .openHour(res.getOpenHour())
                    .closingHour(res.getClosingHour())
                    .picture(res.getPicture())
                    .district(res.getDistrict().getDistrictName())
                    .foodBrand(res.getFoodBrand() == null ? null : res.getFoodBrand().getFoodBrand())
                    .build();

            resultList.add(customRestaurantStatisticDTO);
        }
        return resultList;
    }

    public CustomRestaurantStatisticDTO getByRestaurantName(String restaurantName) {
        Restaurant restaurant = restaurantRepository.getByRestaurantName(restaurantName)
                .orElseThrow(PersonalProjectException::restaurantNotFound);

        CustomRestaurantStatisticDTO customRestaurantStatisticDTO = CustomRestaurantStatisticDTO.builder()
                .restaurantName(restaurant.getRestaurantName())
                .restaurantAddress(restaurant.getRestaurantAddress())
                .telephoneNumber(restaurant.getTelephoneNumber())
                .openHour(restaurant.getOpenHour())
                .closingHour(restaurant.getClosingHour())
                .picture(restaurant.getPicture())
                .district(restaurant.getDistrict().getDistrictName())
                .foodBrand(restaurant.getFoodBrand() == null ? null : restaurant.getFoodBrand().getFoodBrand())
                .build();

        return customRestaurantStatisticDTO;
    }

    public void deleteByRestaurantName(String restaurantName) {
        Restaurant restaurant = restaurantRepository.getByRestaurantName(restaurantName)
                .orElseThrow(PersonalProjectException::restaurantNotFound);

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<CustomRestaurantStatisticDTO> getRestaurantByDishCategory(String dishCategory, String districtName, Pageable pageable) {

        dishCategory = "%" + dishCategory + "%";
        districtName = "%" + districtName + "%";

        List<CustomRestaurantStatisticDTO> customRestaurantStatisticDTOList = new ArrayList<>();

        List<Restaurant> restaurantList = restaurantRepository.getRestaurantByDishCategory(dishCategory, districtName, pageable);

        for (Restaurant res : restaurantList) {
            CustomRestaurantStatisticDTO customRestaurantStatisticDTO = CustomRestaurantStatisticDTO.builder()
                    .restaurantName(res.getRestaurantName())
                    .restaurantAddress(res.getRestaurantAddress())
                    .description(res.getDescription())
                    .telephoneNumber(res.getTelephoneNumber())
                    .openHour(res.getOpenHour())
                    .closingHour(res.getClosingHour())
                    .picture(res.getPicture())
                    .district(res.getDistrict().getDistrictName())
                    .foodBrand(res.getFoodBrand() == null ? null : res.getFoodBrand().getFoodBrand())
                    .build();

            customRestaurantStatisticDTOList.add(customRestaurantStatisticDTO);
        }
        return customRestaurantStatisticDTOList;
    }

    @Override
    public List<Object[]> getNumberOfRestaurantByDistrict() {
        return restaurantRepository.getNumberOfRestaurantByDistrict();
    }

    @Override
    public List<CustomRestaurantStatisticDTO> getByRatingOpenHourAndClosingHour(double rating, String openHour, String closingHour) {
        if (!isValidTimeFormat(openHour))
            throw PersonalProjectException.badRequest("WrongTimeFormat", "Time format must be hh:mm:ss");

        if (!isValidTimeFormat(closingHour))
            throw PersonalProjectException.badRequest("WrongTimeFormat", "Time format must be hh:mm:ss");

        LocalTime open =  LocalTime.parse(openHour);
        LocalTime closing = LocalTime.parse(closingHour);

        List<CustomRestaurantStatisticDTO> results = new ArrayList<>();

        List<Restaurant> raw = restaurantRepository.getByRatingOpenHourAndClosingHour(rating, open, closing);
        for (Restaurant restaurant : raw) {
            CustomRestaurantStatisticDTO dto = CustomRestaurantStatisticDTO.builder()
                    .restaurantName(restaurant.getRestaurantName())
                    .restaurantAddress(restaurant.getRestaurantAddress())
                    .description(restaurant.getDescription())
                    .telephoneNumber(restaurant.getTelephoneNumber())
                    .openHour(restaurant.getOpenHour())
                    .closingHour(restaurant.getClosingHour())
                    .picture(restaurant.getPicture())
                    .district(restaurant.getDistrict().getDistrictName())
                    .foodBrand(restaurant.getFoodBrand() == null ? null : restaurant.getFoodBrand().getFoodBrand())
                    .build();

            results.add(dto);
        }
        return results;
    }


    public boolean isValidTimeFormat(String timeString) {
        String pattern = "^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$";
        return timeString.matches(pattern);
    }

    public LocalTime formatStringToLocaltime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        return LocalTime.parse(time, formatter);
    }
}
