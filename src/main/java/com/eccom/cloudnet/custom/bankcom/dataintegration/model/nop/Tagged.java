package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Edge("t_tagged")
public class Tagged {

    @Id
    private String id;

    @From
    private TagValue tag;

    @To
    private ConfigurationItem configurationItem;
}
