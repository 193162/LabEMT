package com.example.emtlab2.service;

import com.example.emtlab2.model.Author;
import com.example.emtlab2.model.Country;
import com.example.emtlab2.model.dto.CountryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> findByName(String name);
    Optional<Country> edit(Long id, String name, String continent);
    Optional<Country> edit(Long id, CountryDto countryDto);
    Optional<Country> save(String name, String continent);
    Optional<Country> save(CountryDto countryDto);
    void deleteById(Long id);
    void refreshMaterializedView();
}
