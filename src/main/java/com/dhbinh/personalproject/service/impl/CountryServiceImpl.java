package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Country;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.CountryMapper;
import com.dhbinh.personalproject.repository.CountryRepository;
import com.dhbinh.personalproject.service.CountryService;
import com.dhbinh.personalproject.service.dto.CountryDTO;
import com.dhbinh.personalproject.service.dto.UserWithMostCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryDTO createCountry(CountryDTO countryDTO) {

        Optional<Country> existingCountry = countryRepository.findById(countryDTO.getName().trim());
        if (existingCountry.isPresent()) {
            throw PersonalProjectException.badRequest("CountryExisted", "Country name is already existed");
        }

        Country country = Country.builder()
                .name(countryDTO.getName().trim())
                .build();

        return countryMapper.toDTO(countryRepository.save(country));
    }

    public List<CountryDTO> getAllCountry() {

        List<Country> countryList = countryRepository.findAll();
        if (countryList.isEmpty())
            throw PersonalProjectException.countryNotFound();

        return countryMapper.toDTOs(countryList);
    }

    public CountryDTO getByCountryID(String countryName) {

        Country existingCountry = countryRepository.findById(countryName)
                .orElseThrow(PersonalProjectException::countryNotFound);

        return (countryMapper.toDTO(existingCountry));
    }

    public CountryDTO updateByCountryID(CountryDTO countryDTO) {

        Country existingCountry = countryRepository.findById(countryDTO.getName().trim()).
                orElseThrow(PersonalProjectException::countryNotFound);

        existingCountry.setName(countryDTO.getName() == null ? existingCountry.getName() : countryDTO.getName());

        return countryMapper.toDTO(countryRepository.save(existingCountry));
    }

    public void deleteByCountryID(String countryName) {

        Country existingCountry = countryRepository.findById(countryName.trim()).
                orElseThrow(PersonalProjectException::countryNotFound);

        countryRepository.delete(existingCountry);
    }
}
