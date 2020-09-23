package com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop;

import com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop.ConfigurationItem;
import com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop.ResourceGroup;
import com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop.TagInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class CiDto extends ConfigurationItem {          //NOSONAR

    @ApiModelProperty(value = "关联CI,key为关联编码;value为关联CI列表")
    private Map<String, List<RelatedCI>> relatedCI;

    @ApiModelProperty(value = "标签，目前在查询CI时有效")
    private List<TagInfo> tags;

    @ApiModelProperty(value = "CI所属资源组（仅在查询CI时有效）")
    private List<ResourceGroup> resourceGroups;

    @ApiModelProperty(value = "CI所属业务组（仅在查询CI时有效）")
    private List<String> bussinessGroups;

    @ApiModelProperty(value = "下级关联CI是否全量更新")
    private Boolean edgeFullUpdate;     //拓扑关联是否全量更新

    @ApiModelProperty(value = "CI如果存在是否更新")
    private Boolean orUpdate;           //如果CI存在是否转update

    @Getter
    @Setter
    public static class RelatedCI {

        @ApiModelProperty(value = "关联配置项的模型编码")
        private String collection;

        @ApiModelProperty(value = "关联配置项的ID")
        private String id;

        @ApiModelProperty(value = "关联ID")
        private String edgeId;

        @ApiModelProperty(value = "关联配置项属性(修改关联CI时不需要传入)")
        private Map<String, Object> ciProperties;

        @ApiModelProperty(value = "关联属性")
        private Map<String, Object> edgeProperties;

        @ApiModelProperty(value = "下级关联CI是否全量更新")
        private Boolean edgeFullUpdate;                               //下级关联CI是否全量更新

        @ApiModelProperty(value = "关联CI存在时是否更新")
        private Boolean update;

        private List<TagInfo> tags;

        @ApiModelProperty(value = "下级关联CI表，key-关联编码，value-关联的CI列表，仅新建或修改CI时可用")
        private Map<String, List<RelatedCI>> subRelatedCI;      //下级关联CI
    }

    public CiDto(ConfigurationItem configurationItem) {
        super(configurationItem.getId(), configurationItem.getCollection(), configurationItem.getProperties());
    }

    public CiDto(String id, String collection, Map<String, Object> properties) {
        super(id, collection, properties);
    }

    public CiDto() {
        //nothing
    }

    /**
     * 添加关联CI
     *
     * @param relationCode 关联编码
     * @param relatedCI    关联CI
     */
    @ApiModelProperty(hidden = true)
    public void addRelatedCI(String relationCode, RelatedCI relatedCI) {
        if (this.relatedCI == null) {
            this.relatedCI = new HashMap<>();
        }
        if (this.relatedCI.containsKey(relationCode)) {
            this.relatedCI.get(relationCode).add(relatedCI);
        } else {
            List<RelatedCI> relatedCIList = new ArrayList<>();
            relatedCIList.add(relatedCI);
            this.relatedCI.put(relationCode, relatedCIList);
        }
    }

    /**
     * 构建关联CI
     *
     * @param collection 关联ci模型编码
     * @param properties 关联ci属性
     */
    public RelatedCI buildRelatedCI(String collection, String id, Map<String, Object> properties) {
        RelatedCI relatedCi = new RelatedCI();
        relatedCi.setId(id);
        relatedCi.setCollection(collection);
        relatedCi.setCiProperties(properties);
        return relatedCi;
    }
}
