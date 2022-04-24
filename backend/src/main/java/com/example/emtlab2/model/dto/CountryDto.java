package com.example.emtlab2.model.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class CountryDto {

    private String name;

    private String continent;

    public CountryDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public CountryDto() {
    }
}
