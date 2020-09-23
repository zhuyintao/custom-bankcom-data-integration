package com.eccom.cloudnet.custom.bankcom.dataintegration.controller.nop;

import com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop.Result;
import com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop.NspNopItem;
import com.eccom.cloudnet.custom.bankcom.dataintegration.service.nop.NopCmdbService;
import com.eccom.cloudnet.custom.common.model.feign.User;
import com.eccom.cloudnet.custom.common.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nop/cmdb")
public class NopCmdbController {
    @Autowired
    private NopCmdbService nopCmdbService;
    @RequestMapping(value = "/synch", method = {RequestMethod.POST})
    @ApiOperation(value = "数据同步")
    public List<Result> SyncData(@RequestBody @Valid LinkedList<LinkedHashMap<String,Object>> data, @RequestHeader(name = "X-Auth-Token", required = false)String token){
        List<Result> lists = null;
        try {
            lists = nopCmdbService.syncData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

}
