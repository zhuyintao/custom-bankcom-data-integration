package com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CISearchDto extends SearchDto {

    @ApiModelProperty(value = "筛选条件，key为筛选字段，value为筛选值。如果要根据多选类型筛选，则将筛选值以List格式传入value；如果要根据时间类型筛选，则value格式为：{type: \"datetime\", start: \"start_time\", end: \"end_time\"}.其中，时间以时间戳格式传入，如果筛选属性是关联属性，key格式为 <model>_<element>_")
    private Map<String, Object> filters = new HashMap<>();

    @ApiModelProperty(value = "精确查找条件，key为筛选字段，value为筛选值")
    private Map<String, Object> exactFilters;

    @ApiModelProperty(value = "标签筛选条件,key--标签键编码,value--标签值ID列表")
    private Map<String, List<String>> tags;

    @ApiModelProperty(value = "定制返回属性")
    private List<String> propertyCode;

    @ApiModelProperty(value = "是否查询关联CI")
    private Boolean withRelatedCI;

    @ApiModelProperty(value = "查询的关联编码，当需要查询关联CI且关联编码为空时，查询所有关联")
    private List<String> relationCodes;

    @ApiModelProperty(value = "是否查询所属资源组")
    private Boolean withResourceGroup;

    @ApiModelProperty(value = "资源组范围筛选条件，如果是多个资源组，结果取并集，如果不以资源组筛选，请传NULL")
    private List<String> resourceGroupIds;

    @ApiModelProperty(value = "是否查询所属业务组")
    private Boolean withBussinessGroup;

    @ApiModelProperty(value = "业务组筛选条件。当此参数不为空时，资源组范围筛选条件为该业务组下的所有资源组，并默认查询CI关联资源组")
    private Integer bussinessGroupId;

    @ApiModelProperty(value = "是否查询所有属性（仅在查询资源组下CI接口有效）")
    private Boolean withAllProperties;

    //如果有属性需要精确查找，在模糊查找条件中移除该属性
    @ApiModelProperty(hidden = true)
    public void compareFilters() {
        if (this.filters != null && this.exactFilters != null) {
            for (Map.Entry<String, Object> exactFilter : this.exactFilters.entrySet()) {
                this.filters.remove(exactFilter.getKey());
            }
        }
    }

}
