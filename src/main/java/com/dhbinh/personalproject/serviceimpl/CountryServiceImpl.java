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

    public CountryDTO createCountry(CountryDTO countryDTO) {

        Optional<Country> existingCountry = countryRepository.findById(countryDTO.getCountryName());
        if (existingCountry.isPresent()) {
            throw PersonalProjectException.badRequest("CountryExisted", "Country is already existed");
        }

        Country country = Country.builder()
                .countryName(countryDTO.getCountryName())
                .build();

        return countryMapper.toDTO(countryRepository.save(country));
    }

    public List<CountryDTO> getAllCountry() {
        List<Country> countryList = countryRepository.findAll();
        if (countryList.isEmpty())
            throw PersonalProjectException.countryNotFound();

        return countryMapper.toDTOs(countryList);
    }

    public Optional<CountryDTO> getByCountryID(String countryName) {
        Country existingCountry = countryRepository.findById(countryName)
                .orElseThrow(PersonalProjectException::countryNotFound);

        return Optional.ofNullable(countryMapper.toDTO(existingCountry));
    }

    public CountryDTO updateByCountryID(CountryDTO countryDTO) {
         Country existingCountry = countryRepository.findById(countryDTO.getCountryName()).
                 orElseThrow(PersonalProjectException::countryNotFound);

        existingCountry.setCountryName(countryDTO.getCountryName());

        return countryMapper.toDTO(countryRepository.save(existingCountry));
    }

    public void deleteByCountryID(String countryName) {

        Country existingCountry = countryRepository.findById(countryName).
                orElseThrow(PersonalProjectException::countryNotFound);

        countryRepository.deleteById(countryName);
    }
}
