package com.train.merged_pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndicatorWithCountry {
    private Integer id;
    private Integer id_indicator;
    private String indicatorCode;
    private String indicatorName;
    private Integer id_country;
    private String countryName;
    private String year;
    private String value;
}
