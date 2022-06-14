package com.train.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IndicatorsDto {
    @ApiModelProperty(value = "Идентификатор группы", example = "1", dataType = "int")
    private Integer id_groups;
    @ApiModelProperty(value = "Код индикатора", example = "SP.POP.TOTL")
    private String code;
    @ApiModelProperty(value = "Полное название индикатора", example = "Population, total")
    private String name;

    @ApiModelProperty(value = "Флаг существования индикатора", example = "true/false")
    private boolean isExists;

    public IndicatorsDto(){}

    public IndicatorsDto(Integer id_groups, String code, String name){
        id_groups = this.id_groups;
        code = this.code;
        name = this.name;
    }
}
