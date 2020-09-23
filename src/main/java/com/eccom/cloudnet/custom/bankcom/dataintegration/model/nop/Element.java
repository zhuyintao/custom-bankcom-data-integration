package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import com.arangodb.springframework.annotation.Document;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Getter
@Setter
@Document("t_element")
public class Element {
    @Id
    @ApiModelProperty(value = "属性ID，不可修改")
    private String id;

    @ApiModelProperty(value = "属性名称")
    private String name;

    @ApiModelProperty(value = "属性编码")
    private String code;

    @ApiModelProperty(value = "属性类型，不可修改")
    private String type;

    @ApiModelProperty(value = "默认值")
    private Object defaultValue;

    @ApiModelProperty(value = "是否必填")
    private boolean required;

    @ApiModelProperty(value = "是否可见")
    private boolean visible;

    @ApiModelProperty(value = "是否可修改")
    private boolean editable;

    @ApiModelProperty(value = "是否可以此属性查找")
    private boolean searchAble;

    @ApiModelProperty(value = "是否可以此属性排序")
    private boolean sortAble;

    private boolean customShowAble;

    @ApiModelProperty(value = "列表中是否可见")
    private boolean visibleInTable;

    @ApiModelProperty(value = "列表中宽度")
    private Integer columnWidth;

    @ApiModelProperty(value = "纳管")
    private boolean tonetWork;

    @ApiModelProperty(value = "扩展信息")
    private Map<String, Object> extend;

}
