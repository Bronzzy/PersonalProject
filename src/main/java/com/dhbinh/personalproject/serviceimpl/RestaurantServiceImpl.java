package com.dhbinh.personalproject.serviceimpl;

import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.RestaurantMapper;
import com.dhbinh.personalproject.repository.RestaurantRepository;
import com.dhbinh.personalproject.serviceimpl.dto.RestaurantStatisticDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;


    public List<RestaurantStatisticDTO> getAllRestaurant() {
        List<RestaurantStatisticDTO> resultList = new ArrayList<>();

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        if (restaurantList.isEmpty()) {
            throw PersonalProjectException.restaurantNotFound();
        }

        for (Restaurant res : restaurantList) {
            RestaurantStatisticDTO restaurantStatisticDTO = new RestaurantStatisticDTO();

            restaurantStatisticDTO = RestaurantStatisticDTO.builder()
                    .restaurantName(res.getName())
                    .restaurantAddress(res.getAddress())
                    .telephoneNumber(res.getTelephoneNumber())
                    .openHour(res.getOpenHour())
                    .closingHour(res.getClosingHour())
                    .picture(res.getPicture())
                    .district(res.getDistrict().getName())
                    .foodBrand(res.getFoodBrand() == null ? null : res.getFoodBrand().getName())
                    .build();

            resultList.add(restaurantStatisticDTO);
        }
        return resultList;
    }

    public RestaurantStatisticDTO getByRestaurantName(String restaurantName) {
        Restaurant restaurant = restaurantRepository.getByRestaurantName(restaurantName)
                .orElseThrow(PersonalProjectException::restaurantNotFound);

        RestaurantStatisticDTO restaurantStatisticDTO = RestaurantStatisticDTO.builder()
                .restaurantName(restaurant.getName())
                .restaurantAddress(restaurant.getAddress())
                .telephoneNumber(restaurant.getTelephoneNumber())
                .openHour(restaurant.getOpenHour())
                .closingHour(restaurant.getClosingHour())
                .picture(restaurant.getPicture())
                .district(restaurant.getDistrict().getName())
                .foodBrand(restaurant.getFoodBrand() == null ? null : restaurant.getFoodBrand().getName())
                .build();

        return restaurantStatisticDTO;
    }

    public void deleteByRestaurantName(String restaurantName) {
        Restaurant restaurant = restaurantRepository.getByRestaurantName(restaurantName)
                .orElseThrow(PersonalProjectException::restaurantNotFound);

        restaurantRepository.delete(restaurant);
    }

    public List<RestaurantStatisticDTO> getRestaurantByDishCategory(String dishCategory, String districtName){

        dishCategory = "%" + dishCategory + "%";
        districtName = "%" + districtName + "%";
        return restaurantRepository.getRestaurantByDishCategory(dishCategory, districtName);
    }
}
