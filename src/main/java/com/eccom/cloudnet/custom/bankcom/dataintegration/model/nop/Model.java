package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndexed;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.text.Collator;
import java.util.Locale;

@Getter
@Setter
@Document("t_model")
public class Model implements Comparable<Model> {

    @Id
    @ApiModelProperty(value = "模型ID，不可修改")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @HashIndexed
    @ApiModelProperty(value = "编码，不可修改")
    private String code;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "状态 0--禁用，1--启用")
    private Integer status;

    @ApiModelProperty(value = "类型  0---系统内置 1---自定义，不可修改")
    private Integer type;       //0---系统内置 1---自定义

    @ApiModelProperty(value = "编排是否可用", required = true)
    private Boolean orchestrateAble;

    @ApiModelProperty(value = "是否可建配置项", required = true)
    private Boolean instanceAble;

    @ApiModelProperty(value = "资源组是否可见", required = true)
    private Boolean resourceGroupVisible;

    @Override
    public int compareTo(Model o) {
        if (o == null || o.getName() == null) {
            return 1;
        } else if (this.name == null) {
            return -1;
        } else {
            return Collator.getInstance(Locale.CHINESE).compare(this.getName(), o.getName());
        }
    }
}
