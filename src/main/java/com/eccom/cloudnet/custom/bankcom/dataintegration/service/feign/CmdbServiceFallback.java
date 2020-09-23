package com.eccom.cloudnet.custom.bankcom.dataintegration.service.feign;

import com.alibaba.fastjson.JSONObject;
import com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
@Component("cmdbService")
@Slf4j
public class CmdbServiceFallback implements CMDBService {
    @Override
    public Result<String> createCI(String code, CiDto configurationItem) {
        return Result.<String>builder().code(Result.FAILED_CODE).error("fail to execute createCI").build();
    }

    @Override
    public Result<Void> updateCI(String code, String id, PropertiesDto propertiesDto) {
        return Result.<Void>builder().code(Result.FAILED_CODE).error("fail to execute updateCI").build();
    }

    @Override
    public Result<CiDto> findCIById(String code, String id, Integer withRelation, Boolean withResourceGroup, Boolean withBussinessGroup) {
        return Result.<CiDto>builder().code(Result.FAILED_CODE).error("fail to execute findCI").build();
    }
    @Override
    public Result<PageInfo> listCI(@PathVariable(value = "code") String code, @RequestBody CISearchDto searchDto) {
        return Result.<PageInfo>builder().code(Result.FAILED_CODE).error("fail to execute listCI").build();
    }
    @Override
    public Result<Void> updateCIBatch(@PathVariable(value = "code") String code, @RequestBody CIUpdateDto ciUpdateDto){
        return Result.<Void>builder().code(Result.FAILED_CODE).error("fail to execute updateCIBatch").build();
    }
    @Override
    public Result<List<String>> createCiBatch(@RequestBody List<CiDto> configurationItems){
        return Result.<List<String>>builder().code(Result.FAILED_CODE).error("fail to execute createCiBatch").build();
    }
    @Override
    public Result<Void> updateCIWithPropertiesBatch(@PathVariable(value = "code") String code, @RequestBody List<CIUpdateBatchDto> ciUpdateBatchDtos){
        return Result.<Void>builder().code(Result.FAILED_CODE).error("fail to execute updateCIWithPropertiesBatch").build();
    }
}

