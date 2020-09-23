package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BussinessGroup {

    private String id;

    private String name;

    private String parentId;

    private BussinessGroup parent;

}
