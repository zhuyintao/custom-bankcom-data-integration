package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import com.arangodb.springframework.annotation.Document;
import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class TagInfo {

    private String keyId;
    private String valueId;
    private String code;
    private String name;
    private String color;
    private String value;
}
