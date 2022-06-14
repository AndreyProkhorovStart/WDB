package com.train.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EconomyGrowthDto {
    @ApiModelProperty(value = "Идентификатор страны", example = "233", dataType = "int")
    private Integer id_country;
    @ApiModelProperty(value = "Год", example = "2000", dataType = "string")
    private String year;
    @ApiModelProperty(value = "Значение показателя", example = "543245", dataType = "int")
    private String value;
    @ApiModelProperty(value = "Идентификатор индикатора", example = "381", dataType = "int")
    private Integer id_indicator;
    @ApiModelProperty(value = "Начало года", example = "2000", dataType = "string")
    private String startYear;
    @ApiModelProperty(value = "Конец года", example = "2010", dataType = "string")
    private String endYear;

    public EconomyGrowthDto(){}

    public EconomyGrowthDto(String startYear, String endYear){
        startYear = this.startYear;
        endYear = this.endYear;
    }

    public EconomyGrowthDto(Integer id_country, String year, String value, Integer id_indicator) {
        id_country = this.id_country;
        year = this.year;
        value = this.value;
        id_indicator = this.id_indicator;
    }

    public EconomyGrowthDto(Integer id_country, String year, String value){
        id_country = this.id_country;
        year = this.year;
        value = this.value;
    }
}