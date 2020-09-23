package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import lombok.Getter;

@Getter
public enum Device {
    Router("1","Router"),
    Switch("2","Switch"),
    Firewall("3","Firewall"),
    LoadBalancer("5","LoadBalancer"),
    app("33","app");
    private String code;
    private String desc;
    private Device(String code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
