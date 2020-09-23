package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import com.arangodb.springframework.annotation.Document;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Document
@Getter
@Setter
public class TagValue {

    @Id
    private String id;

    private String keyCode;

    private String value;

}
