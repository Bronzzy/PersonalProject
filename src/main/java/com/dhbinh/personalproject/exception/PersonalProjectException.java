package com.dhbinh.personalproject.exception;

import org.springframework.http.HttpStatus;

public class PersonalProjectException {

    private static final String COUNTRY_NOT_FOUND_MSG_KEY = "CountryNotExisted";
    private static final String COUNTRY_NOT_FOUND_MSG = "Country not found";
    private static final String CITY_NOT_FOUND_MSG_KEY = "CityNotExisted";
    private static final String CITY_NOT_FOUND_MSG = "City not found";
private static final String DISTRICT_NOT_FOUND_MSG_KEY = "DistrictNotExisted";
    private static final String DISTRICT_NOT_FOUND_MSG = "District not found";


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

    public static ResponseException districtNotFound(){
        return notFound(DISTRICT_NOT_FOUND_MSG_KEY,DISTRICT_NOT_FOUND_MSG);
    }
}
