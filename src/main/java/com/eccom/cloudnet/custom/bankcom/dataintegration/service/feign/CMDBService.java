package com.eccom.cloudnet.custom.bankcom.dataintegration.service.feign;

import com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@FeignClient(value ="common-cmdb",fallback = CmdbServiceFallback.class)
@Service
public interface CMDBService {
    @PostMapping("/model/{code}/ci/query")
    Result<PageInfo> listCI(@PathVariable(value = "code") String code, @RequestBody CISearchDto searchDto);
    @PostMapping("/model/{code}/ci")
    Result<String> createCI(@PathVariable(value = "code") String code, @RequestBody CiDto configurationItem);
    @PutMapping("/model/{code}/ci/{id}")
    Result<Void> updateCI(@PathVariable(value = "code") String code, @PathVariable(value = "id") String id, @RequestBody PropertiesDto propertiesDto);
    @GetMapping("/model/{code}/ci/{id}")
    Result<CiDto> findCIById(@PathVariable(value = "code") String code,
                             @PathVariable(value = "id") String id,
                             @RequestParam(value = "withRelation", required = false) Integer withRelation,
                             @RequestParam(value = "withResourceGroup", required = false) Boolean withResourceGroup,
                             @RequestParam(value = "withBussinessGroup", required = false) Boolean withBussinessGroup);

    @PostMapping("/model/ci/batch")
    Result<List<String>> createCiBatch(@RequestBody List<CiDto> configurationItems);
    @PutMapping("/model/{code}/ci")
    @ApiOperation("批量修改CI属性")
    Result<Void> updateCIBatch(@PathVariable(value = "code") String code, @RequestBody CIUpdateDto ciUpdateDto);
    @PutMapping("/model/{code}/ci/properties")
    @ApiOperation("批量修改CI(每个CI单独指定更新属性)")
    Result<Void> updateCIWithPropertiesBatch(@PathVariable(value = "code") String code, @RequestBody List<CIUpdateBatchDto> ciUpdateBatchDtos);
}
