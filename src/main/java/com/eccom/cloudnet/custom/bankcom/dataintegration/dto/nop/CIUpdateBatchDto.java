package com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CIUpdateBatchDto extends PropertiesDto {

    @ApiModelProperty("待修改的配置项的id")
    private String id;
}
