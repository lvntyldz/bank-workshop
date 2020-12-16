package com.ba.controller;

import com.ba.domain.Country;
import com.ba.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/list")
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @GetMapping("/search")
    public Page<Country> searchCountries(@RequestParam String countryName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return countryRepository.findAllByNameContainsIgnoreCase(countryName, pageable);
    }

    @GetMapping("/v2/search")
    public Slice<Country> searchCountriesV2(@RequestParam String countryName, Pageable pageable) {
        return countryRepository.findAllByNameContainsIgnoreCase(countryName, pageable);
    }
}
