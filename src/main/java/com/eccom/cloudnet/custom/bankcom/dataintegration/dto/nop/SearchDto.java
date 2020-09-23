package com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Min;

@Getter
@Setter
public class SearchDto {

    @ApiModelProperty(value = "当前页数")
    @Min(0)
    private Integer pageNum;

    @ApiModelProperty(value = "每页条数，0表示不限制")
    @Min(0)
    private Integer pageSize;

    @ApiModelProperty(value = "排序字段，默认为 _id")
    private String sort;

    @ApiModelProperty(value = "顺序，正序——ASC，倒序——DESC")
    private String order;

    @ApiModelProperty(value = "全字段模糊查询条件")
    private String query;

    public Integer getPageNum() {
        if (this.pageNum == null || this.pageNum < 1) {
            return 1;
        } else {
            return this.pageNum;
        }
    }

    public Integer getPageSize() {
        if (this.pageSize == null || this.pageSize < 0) {
            return 0;
        } else {
            return this.pageSize;
        }
    }

    public String getSort() {
        if (StringUtils.isEmpty(this.sort)) {
            return "_id";
        } else {
            return sort;
        }
    }

    public String getOrder() {
        if (StringUtils.isEmpty(this.order)) {
            return "ASC";
        } else {
            return this.order.toUpperCase();
        }
    }

}
