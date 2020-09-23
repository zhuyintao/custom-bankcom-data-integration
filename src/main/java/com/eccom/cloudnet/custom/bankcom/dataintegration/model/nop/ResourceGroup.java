package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Document("t_resource_group")
public class ResourceGroup {
    @Id
    private String id;

    private String name;

    private String description;

    private Map<String, Object> attrs = new HashMap<>();

    private List<BussinessGroup> bussinessGroups = new ArrayList<>();

    //ResourceGroup默认展示的字段信息
    private List<Property> properties = new ArrayList<>();

    @Relations(edges = ResourceModel.class, direction = Relations.Direction.OUTBOUND)
    private List<Model> relevantModels;

    @Relations(edges = Tagged.class, direction = Relations.Direction.INBOUND)
    private List<TagValue> tags;

    @ApiModelProperty(hidden = true)
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("resourceGroupId", this.id);
        map.put("resourceGroupName", this.name);
        map.put("attr", this.attrs);
        map.put("properties", this.properties);
        map.put("relevantModels", this.relevantModels);
        map.put("tags", this.tags);
        return map;
    }

    @Getter
    @Setter
    public static class Property {
        String code;
        String name;
        String relatedModel;
        Integer relatedDepth;
        List<String> relationCodes;
        private com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop.Property.RelatedElement relatedElement;

        public Property(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public Property() {
        }
    }
}
