package com.dhbinh.personalproject.exception;

import org.springframework.http.HttpStatus;

public class PersonalProjectException {

    private static final String COUNTRY_NOT_FOUND_MSG_KEY = "CountryNotExisted";
    private static final String COUNTRY_NOT_FOUND_MSG = "Country not found";
    private static final String CITY_NOT_FOUND_MSG_KEY = "CityNotExisted";
    private static final String CITY_NOT_FOUND_MSG = "City not found";
    private static final String DISTRICT_NOT_FOUND_MSG_KEY = "DistrictNotExisted";
    private static final String DISTRICT_NOT_FOUND_MSG = "District not found";
    private static final String FOODBRAND_NOT_FOUND_MSG_KEY = "FoodBrandNotExisted";
    private static final String FOODBRAND_NOT_FOUND_MSG = "FoodBrand not found";
    private static final String RESTAURANT_NOT_FOUND_MSG_KEY = "RestaurantNotExisted";
    private static final String RESTAURANT_NOT_FOUND_MSG = "Restaurant not found";
    private static final String POST_NOT_FOUND_MSG_KEY = "PostNotExisted";
    private static final String POST_NOT_FOUND_MSG = "Post not found";
    private static final String ADMINACCOUNT_NOT_FOUND_MSG_KEY = "AdminNotExisted";
    private static final String ADMINACCOUNT_NOT_FOUND_MSG = "Admin not found";
    private static final String COMMENT_NOT_FOUND_MSG_KEY = "AdminNotExisted";
    private static final String COMMENT_NOT_FOUND_MSG = "Admin not found";
    private static final String DISHCATEGORY_NOT_FOUND_MSG_KEY = "DishCategoryNotExisted";
    private static final String DISHCATEGORY_NOT_FOUND_MSG = "DishCategory not found";
    private static final String MENU_NOT_FOUND_MSG_KEY = "MenuNotExisted";
    private static final String MENU_NOT_FOUND_MSG = "Menu not found";


    public static ResponseException notFound(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.NOT_FOUND);
    }

    public static ResponseException badRequest(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseException internalServerError(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseException countryNotFound() {
        return notFound(COUNTRY_NOT_FOUND_MSG_KEY, COUNTRY_NOT_FOUND_MSG);
    }

    public static ResponseException cityNotFound() {
        return notFound(CITY_NOT_FOUND_MSG_KEY, CITY_NOT_FOUND_MSG);
    }

    public static ResponseException districtNotFound() {
        return notFound(DISTRICT_NOT_FOUND_MSG_KEY, DISTRICT_NOT_FOUND_MSG);
    }

    public static ResponseException foodBrandNotFound() {
        return notFound(FOODBRAND_NOT_FOUND_MSG_KEY, FOODBRAND_NOT_FOUND_MSG);
    }

    public static ResponseException restaurantNotFound() {
        return notFound(RESTAURANT_NOT_FOUND_MSG_KEY, RESTAURANT_NOT_FOUND_MSG);
    }

    public static ResponseException postNotFound() {
        return notFound(POST_NOT_FOUND_MSG_KEY, POST_NOT_FOUND_MSG);
    }

    public static ResponseException adminNotFound() {
        return notFound(ADMINACCOUNT_NOT_FOUND_MSG_KEY, ADMINACCOUNT_NOT_FOUND_MSG);
    }

    public static ResponseException commentNotFound() {
        return notFound(COMMENT_NOT_FOUND_MSG_KEY, COMMENT_NOT_FOUND_MSG);
    }

    public static ResponseException dishCategoryNotFound() {
        return notFound(DISHCATEGORY_NOT_FOUND_MSG_KEY, DISHCATEGORY_NOT_FOUND_MSG);
    }

    public static ResponseException menuNotFound() {
        return notFound(MENU_NOT_FOUND_MSG_KEY, MENU_NOT_FOUND_MSG);
    }
}
