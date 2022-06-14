package com.train.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupsDto {
    @ApiModelProperty(value = "Название группы", example = "Climate Change", dataType = "string")
    private String name;

    public GroupsDto(){}

    public GroupsDto (String name){
        name = this.name;
    }
}
