package com.train.merged_pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndicatorWithGroup {
    private Integer id;
    private Integer id_group;
    private String groupName;
    private String code;
    private String name;
}
