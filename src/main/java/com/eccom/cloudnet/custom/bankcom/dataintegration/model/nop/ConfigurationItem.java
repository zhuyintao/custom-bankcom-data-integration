package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import com.arangodb.springframework.annotation.Document;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Document
@Data
@Slf4j
public class ConfigurationItem implements Comparable {

    @Id
    @ApiModelProperty(value = "配置项ID，创建配置项时自动生成，新建配置项操作时为空")
    private String id;

    @ApiModelProperty(value = "所属模型编码（model code）")
    private String collection;

    @ApiModelProperty(value = "配置项属性")
    private Map<String, Object> properties;

    /**
     * 根据配置项ID和关键属性判断两个CI是否相同
     *
     * @param configurationItem 待判断的CI
     * @param keys              模型关键属性列表
     * @return 判断结果
     */
    public boolean equals(ConfigurationItem configurationItem, List<Key> keys) {
        boolean result = true;
        if (id != null) {
            result = id.equals(configurationItem.getId());
        }
        if (collection != null) {
            result = result && collection.equals(configurationItem.getCollection());
        }
        if (!CollectionUtils.isEmpty(keys)) {
            for (Key key : keys) {
                result = result && buildKeyProperty(this.getProperties(), key).equals(buildKeyProperty(configurationItem.getProperties(), key));
            }
        }
        return result;
    }

    private String buildKeyProperty(Map<String, Object> properties, Key key) {
        if (CollectionUtils.isEmpty(properties)) {
            return "";
        }
        StringBuilder keyProperty = new StringBuilder();
        if (!CollectionUtils.isEmpty(key.getElements())) {
            for (Element element : key.getElements()) {
                keyProperty.append(properties.get(element.getCode())).append(":");
            }
            keyProperty.deleteCharAt(keyProperty.length() - 1);
        }
        return keyProperty.toString();
    }

    public ConfigurationItem(String id, String collection) {
        this.id = id;
        this.collection = collection;
    }

    public ConfigurationItem(String id, String collection, Map properties) {
        this.id = id;
        this.collection = collection;
        this.properties = properties;
    }

    public static String buildId(ConfigurationItem configurationItem) {
        return configurationItem.getCollection() + "/" + configurationItem.getId();
    }

    public ConfigurationItem() {
    }

    @Override
    public int compareTo(Object o) {
        if (this.id != null && ((ConfigurationItem) o).getId() != null) {
            return Long.compare(Long.parseLong(this.id), Long.parseLong(((ConfigurationItem) o).getId()));
        } else {
            return -1;
        }
    }

}
