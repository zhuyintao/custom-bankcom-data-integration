package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Edge("t_resource_model")
public class ResourceModel {

    @Id
    private String id;

    @From
    private ResourceGroup resourceGroup;

    @To
    private Model model;

}
