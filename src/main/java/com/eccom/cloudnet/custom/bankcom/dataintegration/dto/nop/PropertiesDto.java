package com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PropertiesDto {

    @ApiModelProperty(value = "修改属性，key——字段名，value——属性值")
    private Map<String, Object> properties;
}
