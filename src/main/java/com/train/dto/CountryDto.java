package com.train.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CountryDto {

    private String code_name;

    private String full_name;

    public CountryDto(){}

    public CountryDto (String full_name){
        full_name = this.full_name;
    }
}
