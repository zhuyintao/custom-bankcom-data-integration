package com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CIUpdateDto extends PropertiesDto {

    @ApiModelProperty(value = "待修改的配置项的ID列表")
    private List<String> updateIds;
}
