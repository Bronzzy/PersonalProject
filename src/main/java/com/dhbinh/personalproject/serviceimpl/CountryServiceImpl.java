package com.dhbinh.personalproject.serviceimpl;

import com.dhbinh.personalproject.entity.Country;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.CountryMapper;
import com.dhbinh.personalproject.repository.CountryRepository;
import com.dhbinh.personalproject.serviceimpl.dto.CountryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryServiceImpl {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryDTO createCountry(CountryDTO countryDTO){

        Optional<Country> existingCountry = countryRepository.getCountryByCountryName(countryDTO.getCountryName());
        if(existingCountry.isPresent()) {
            throw PersonalProjectException.badRequest("CountryNotValid", "Country is already existed");
        }
        Country country = Country.builder()
                .countryName(countryDTO.getCountryName())
                .build();
            countryRepository.save(country);

        return countryMapper.toDTO(country);
    }


    public List<CountryDTO> getAllCountry(){
        List<Country> countryList = countryRepository.findAll();
        if(countryList.isEmpty())
            throw PersonalProjectException.countryNotFound();
        return countryMapper.toDTOs(countryList);
    }

    public CountryDTO getCountryByName(String name){
        Optional<Country> existingCountry = countryRepository.getCountryByCountryName(name);
        if(existingCountry.isEmpty())
            throw PersonalProjectException.notFound("CountryNotFound","Country is not existed");

        return countryMapper.toDTO(existingCountry.get());
    }

    public CountryDTO updateCountry(CountryDTO countryDTO){
        Optional<Country> existingCountry = countryRepository.getCountryByCountryName(countryDTO.getCountryName());
        if(!existingCountry.isPresent())
            throw PersonalProjectException.badRequest("CountryNotFound","Country is not existed");

        Country updateCountry = Country.builder()
                .countryName(countryDTO.getCountryName())
                .build();

        return countryMapper.toDTO(countryRepository.save(updateCountry));
    }
}
