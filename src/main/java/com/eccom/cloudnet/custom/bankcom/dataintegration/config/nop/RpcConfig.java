package com.eccom.cloudnet.custom.bankcom.dataintegration.config.nop;

import com.eccom.cloudnet.custom.bankcom.dataintegration.service.nop.RpcService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcConfig {
    @Value("${nop.rpc.url}")
    public String url;
    @Value("${nop.rpc.db}")
    public String db;
    @Value("${nop.rpc.userName}")
    public String userName;
    @Value("${nop.rpc.passWord}")
    public String passWord;
    @Value("${nop.rpc.moduleIds}")
    public String moduleIds;
    @Value("${nop.rpc.methodIds}")
    public String methodIds;
    @Value("${nop.rpc.moduleDatas}")
    public String moduleDatas;
    @Value("${nop.rpc.methodDatas}")
    public String methodDatas;
    @PostConstruct
    public void init(){
        RpcService.setConfigInfo(this);
    }
}
