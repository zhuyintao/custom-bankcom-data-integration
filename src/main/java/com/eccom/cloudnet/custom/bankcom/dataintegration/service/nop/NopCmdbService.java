package com.eccom.cloudnet.custom.bankcom.dataintegration.service.nop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop.*;
import com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop.Device;
import com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop.FieldPair;
import com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop.NspNopItem;
import com.eccom.cloudnet.custom.bankcom.dataintegration.service.feign.CMDBService;
import com.eccom.cloudnet.custom.bankcom.dataintegration.utils.ToClass;
import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NopCmdbService {
    @Autowired
    private CMDBService cmdbService;
    public static final String CONFIG_PATH="/nsp_nop_map.json";
    private static String uniqueCode="nop_ip";
    //private static String uniqueCode="nop_ip";
//    public List<Result> syncData1() throws Exception {
//        List<Result> lists=new ArrayList<>();
//        //解析配置文件，获取同步配置项
//        List<NspNopItem> nspNopItems = jsonToList();
//        List<LinkedHashMap<String,Object>> nspData=null;
//        System.out.println("nspNopItems:"+nspNopItems);
//
//        HashMap<String, Integer> numHashMap=null;
//        // 逐个执行同步配置项
//        for (NspNopItem nspNopItem : nspNopItems) {
//            //获取对应的nopTable名
//            String nopTable = nspNopItem.getNopTable();
//            List<LinkedHashMap<String,Object>> nopData = getNopDatas(nopTable);
//            if(nopData==null||nopData.size()==0){
//                continue;
//            }
//            //得到对应的nspData
//            nspData = convertNopDataToNsp(nspNopItem, nopData);
//            List<List<LinkedHashMap<String,Object>>> listsnspData=getListsnspData(nspData);
//            System.out.println("nspData："+nspData);
//            int insertNum=0;
//            int updateNum=0;
//            try {
//                for (List<LinkedHashMap<String, Object>> nspDatas : listsnspData) {
//                    HashMap<String, Integer> hashMap = writeToNspCMDB(nspNopItem, nspDatas);
//                    insertNum+=hashMap.get("insertNum");
//                    updateNum+=hashMap.get("updateNum");
//                    numHashMap.put("insertNum",numHashMap.get("insertNum")+hashMap.get("insertNum"));
//                    numHashMap.put("updateNum",numHashMap.get("updateNum")+hashMap.get("updateNum"));
//                }
//                //写入nsp数据
//                //numHashMap = writeToNspCMDB(nspNopItem, nspData);
//
//                log.debug(String.format("%s表于%s 同步成功，其中插入%s条、更新%s条，总数据%s条",
//                        nspNopItem.getNspTable(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),numHashMap.get("insertNum"),numHashMap.get("updateNum"),nspData.size()));
//                lists.add(Result.<String>builder().code(Result.SUCCESS_CODE).res(String.format("%s表于%s 同步成功，其中插入%s条、更新%s条，总数据%s条",
//                        nspNopItem.getNspTable(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),numHashMap.get("insertNum"),numHashMap.get("updateNum"),nspData.size())).build());
//                numHashMap.put("updateNum",0);
//                numHashMap.put("insertNum",0);
//                //log.debug("Switch表于2020-09-07 12:03:22 同步成功，其中插入10条、更新30条、删除20条，总数据60条");
//            } catch (Exception e) {
//                log.debug(String.format("%s表于%s 同步成功，其中插入%s条、更新%s条，总数据%s条",
//                        nspNopItem.getNspTable(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),numHashMap.get("insertNum"),numHashMap.get("updateNum"),nspData.size()));
//
//                lists.add(Result.<String>builder().code(Result.SUCCESS_CODE).res(String.format("%s表于%s 同步成功，其中插入%s条、更新%s条，总数据%s条",
//                        nspNopItem.getNspTable(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),numHashMap.get("insertNum"),numHashMap.get("updateNum"),nspData.size())).error(e.getMessage()).trace(e.getStackTrace().toString()).build());
//
//                //log.debug("Switch表于2020-09-07 12:03:22 同步成功，其中插入10条、更新30条、删除20条，总数据60条");
//                e.printStackTrace();
//            }
//
//        }
//        return lists;
//    }
    /**
     * 数据同步
     * @return
     * @throws Exception
     */
    public List<Result> syncData() throws Exception {
            List<Result> lists=new ArrayList<>();
            //解析配置文件，获取同步配置项
            List<NspNopItem> nspNopItems = jsonToList();
            List<LinkedHashMap<String,Object>> nspData=null;
            System.out.println("nspNopItems:"+nspNopItems);

            HashMap<String, Integer> numHashMap=null;
            // 逐个执行同步配置项
            for (NspNopItem nspNopItem : nspNopItems) {
                //获取对应的nopTable名
                String nopTable = nspNopItem.getNopTable();
                List<LinkedHashMap<String,Object>> nopData = getNopDatas(nopTable);
                if(nopData==null||nopData.size()==0){
                    continue;
                }
                //得到对应的nspData
                nspData = convertNopDataToNsp(nspNopItem, nopData);
                List<List<LinkedHashMap<String,Object>>> listsnspData=getListsnspData(nspData);
                System.out.println("nspData："+nspData);
                Integer insertNum=0;
                Integer updateNum=0;
                try {
                    for (List<LinkedHashMap<String, Object>> nspDatas : listsnspData) {
                       HashMap<String, Integer> hashMap = writeToNspCMDB(nspNopItem, nspDatas);
                       insertNum+=hashMap.get("insertNum");
                       updateNum+=hashMap.get("updateNum");
                       //numHashMap.put("insertNum",numHashMap.get("insertNum")+hashMap.get("insertNum"));
                       //numHashMap.put("updateNum",numHashMap.get("updateNum")+hashMap.get("updateNum"));
                    }
                    //写入nsp数据
                    //numHashMap = writeToNspCMDB(nspNopItem, nspData);

                    log.debug(String.format("%s表于%s 同步成功，其中插入%s条、更新%s条，总数据%s条",
                            nspNopItem.getNspTable(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),insertNum,updateNum,nspData.size()));
                    lists.add(Result.<String>builder().code(Result.SUCCESS_CODE).res(String.format("%s表于%s 同步成功，其中插入%s条、更新%s条，总数据%s条",
                            nspNopItem.getNspTable(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),insertNum,updateNum,nspData.size())).build());
                    //log.debug("Switch表于2020-09-07 12:03:22 同步成功，其中插入10条、更新30条、删除20条，总数据60条");
                } catch (Exception e) {
                    log.debug(String.format("%s表于%s 同步成功，其中插入%s条、更新%s条，总数据%s条",
                            nspNopItem.getNspTable(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),insertNum,updateNum,nspData.size()));

                    lists.add(Result.<String>builder().code(Result.SUCCESS_CODE).res(String.format("%s表于%s 同步成功，其中插入%s条、更新%s条，总数据%s条",
                            nspNopItem.getNspTable(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),insertNum,updateNum,nspData.size())).error(e.getMessage()).trace(e.getStackTrace().toString()).build());

                    //log.debug("Switch表于2020-09-07 12:03:22 同步成功，其中插入10条、更新30条、删除20条，总数据60条");
                    e.printStackTrace();
                }

            }
            return lists;
    }
    //分批处理
    private List<List<LinkedHashMap<String,Object>>> getListsnspData(List<LinkedHashMap<String, Object>> nspData) {
        int a=nspData.size()/5000;
        List<List<LinkedHashMap<String, Object>>> lists = new ArrayList<>();
        for (int i = 0; i < a + 1; i++) {
            List<LinkedHashMap<String, Object>> list = new ArrayList();
                for (int j = 5000 * i; j < 5000 * i + 5000; j++) {
                    if (j <= nspData.size()-1) {
                        list.add(nspData.get(j));
                    }
                }
            lists.add(list);
        }
        return lists;
    }

    /**
     * 解析配置文件
     * @return
     * @throws IOException
     */
    public List<NspNopItem> jsonToList() throws IOException {
        //String path="/nsp_nop_map.json";
        InputStream config = getClass().getResourceAsStream(CONFIG_PATH);
        if (config == null) {
            throw new RuntimeException("读取文件失败");
        } else {
            JSONArray jsonArray = JSON.parseObject(config, JSONArray.class);
            List<NspNopItem> list =jsonArray.toJavaList(NspNopItem.class);
            return list;
        }
    }

    /**
     * 通过表名获取nop表的全量信息
     * @param nopTable nop表名
     * @return
     */
    public List<LinkedHashMap<String,Object>> getNopDatas(String nopTable) throws Exception {
        //TODO 获取数据优化
        Integer id=0;
        Device[] devices = Device.values();
        for (Device device : devices) {
            if(nopTable.equalsIgnoreCase(device.getDesc())){
                id= Integer.valueOf(device.getCode());
            }
        }
        if(id==0){
            throw new Exception("nopTable有误!");
        }
        int uid = RpcService.getConnectForUid();
        if(uid!=-1){
            //('i_id', '=', 1)
            List<Object> lists1=new ArrayList<>();
            List<Object> list1=new ArrayList<>();
            Collections.addAll(list1,"i_id","=",id);
            lists1.add(list1);
            Gson gson = new Gson();
            Object ids =RpcService.getIds(uid,gson.toJson(lists1));
            if (ids != null) {
                List<Object> lists2=new ArrayList<>();
                List<Object> list2=new ArrayList<>();
                Collections.addAll(list2,"id","in",ids);
                lists2.add(list2);
                Object datas = RpcService.getDatas(uid, gson.toJson(lists2));
                if(datas!=null){
                    Object[] objects=(Object[])datas;
                    List<HashMap> list=new ArrayList();
                    for (Object object : objects) {
                        list.add((HashMap) object);
                    }
                    for (HashMap hashMap : list) {
                        getgetNopData(hashMap);
                    }
                    List <LinkedHashMap<String,Object>> lists=new ArrayList<>();
                    for (HashMap hashMap : list) {
                        LinkedHashMap linkedHashMap=new LinkedHashMap(hashMap);
                        lists.add(linkedHashMap);
                    }
                    System.out.println("lists:"+lists);
                    return lists;
                }else {
                    throw new Exception("请求rpc接口异常");
                }
            }else {
                throw new Exception("请求rpc接口异常");
            }
        }else {
            throw new Exception("获取uid异常");
        }
    }

    /**
     * 转化所需格式
     * @param hashMap
     */
    private void getgetNopData(HashMap hashMap) {
        String[] strings={"PLATFORM","MANAGEIP","SERIES","OS","SEQUENCE","FACTORY","TYPE"};
        for (String string : strings) {
            Object o = hashMap.get(string);
            Object[] objects=(Object[])o;
            hashMap.put(string,objects[1]);
        }
    }
    /**
     *
     * @param nspNopItem nopData转化为nspData
     * @param nopData
     * @return
     */
    public List<LinkedHashMap<String,Object>> convertNopDataToNsp(NspNopItem nspNopItem, List<LinkedHashMap<String,Object>> nopData) {
        //映射得出nsp数据
        List<LinkedHashMap<String,Object>> nspList=new ArrayList<>();
        //遍历网络运维平台数据
        for (LinkedHashMap<String, Object> linkedHashMap : nopData) {
            LinkedHashMap<String,Object> nspLinkedHashMap=new LinkedHashMap<>();
            //遍历该表，获取属性值
            List<FieldPair> fieldPairList = nspNopItem.getFieldPair();
            for (FieldPair fieldPair : fieldPairList) {
                Set<String> keySet = linkedHashMap.keySet();
                Iterator<String> iterator = keySet.iterator();
                while (iterator.hasNext()) {
                    String nopKey = iterator.next();
                    if(fieldPair.getNopField().equalsIgnoreCase(nopKey)) {
                        String valueString = linkedHashMap.get(nopKey).toString();
                        String keyString = fieldPair.getNspField();
                        nspLinkedHashMap.put(keyString,valueString);
                    }
                }
            }
            nspList.add(nspLinkedHashMap);
        }
        return nspList;
    }

    /**
     * nspData同步到CMDB中
     * @param nspNopItem
     * @param nspDatas
     * @return
     * @throws Exception
     */
    private HashMap<String,Integer> writeToNspCMDB(NspNopItem nspNopItem, List<LinkedHashMap<String, Object>> nspDatas) throws Exception {
        String nspTable = nspNopItem.getNspTable();
        //获取nsp的全量数据
        LinkedHashMap<String,Object> nspDatasLinkedHashMap=getNspDatas(nspTable);
        List<CiDto> configurationItems=new ArrayList();
        List<CIUpdateBatchDto> ciUpdateBatchDtos=new ArrayList<>();
        Integer updateNum=0;
        Integer insertNum=0;
        //List<String> errMessage=new ArrayList<>();

        for (LinkedHashMap<String, Object> nspData : nspDatas) {
            //判断是否存在

            //运维平台数据是否为null,如果为null,则跳过
            String ciCode = (String) nspData.get(uniqueCode);
            if(ciCode==null){
                continue;
            }
            //不存在批量创建设备，存在则批量更新设备
            boolean containsKey = nspDatasLinkedHashMap.containsKey(ciCode);
            if(containsKey){
                CIUpdateBatchDto ciUpdateBatchDto=new CIUpdateBatchDto();
                LinkedHashMap<String,Object> linkedHashMap=(LinkedHashMap<String,Object>)nspDatasLinkedHashMap.get(ciCode);
                String id = (String) linkedHashMap.get("id");
                ciUpdateBatchDto.setId(id);
                ciUpdateBatchDto.setProperties(nspData);
                ciUpdateBatchDtos.add(ciUpdateBatchDto);
            }else {
                CiDto configurationItem=new CiDto();
                configurationItem.setCollection(nspTable);
                configurationItem.setProperties(nspData);
                configurationItems.add(configurationItem);
            }
        }

        if(configurationItems.size()>0){
            Result<List<String>> createCiBatchResult = cmdbService.createCiBatch(configurationItems);
            System.out.println(createCiBatchResult);

            if(createCiBatchResult!=null&&createCiBatchResult.getCode()==0){
                //批量创建成功
                insertNum = createCiBatchResult.getRes().size();
            }else {
                //批量创建失败
                throw new Exception("批量创建Ci失败");
            }
        }

        if(ciUpdateBatchDtos.size()>0){
            Result<Void> updateCIBatchResult = cmdbService.updateCIWithPropertiesBatch(nspTable, ciUpdateBatchDtos);
            if(updateCIBatchResult.getCode()==0){
                //批量修改成功
                updateNum = ciUpdateBatchDtos.size();
            }else {
                //批量创建失败
                throw new Exception("批量创建Ci失败");
            }
        }

        HashMap<String,Integer> hashMap=new HashMap<>();
        hashMap.put("updateNum",updateNum);
        hashMap.put("insertNum",insertNum);
        return hashMap;
    }

    /**
     * 获取nsp全量数据
     * @param nspTable
     * @return
     */
    private LinkedHashMap<String, Object> getNspDatas(String nspTable) {
        LinkedHashMap<String,Object> nspDatasLinkedHashMap=new LinkedHashMap<>();
        CISearchDto ciSearchDto=new CISearchDto();
        Result<PageInfo> infoResult = cmdbService.listCI(nspTable, ciSearchDto);
        System.out.println(infoResult);
        List<LinkedHashMap<String,Object>> lists = infoResult.getRes().getList();
        for (LinkedHashMap<String, Object> hashMap : lists) {
            LinkedHashMap<String,Object> propertiesLinkedHashMap = (LinkedHashMap<String,Object>)hashMap.get("properties");
            String nop_ciCode =(String) propertiesLinkedHashMap.get("nop_ciCode");
            nspDatasLinkedHashMap.put(nop_ciCode,propertiesLinkedHashMap);
        }
        System.out.println(nspDatasLinkedHashMap);
        return nspDatasLinkedHashMap;
    }
    /**
     * 创建设备
     * @param nspTable
     * @param nspData
     */
    private Result<String> createCI(String nspTable, LinkedHashMap<String, Object> nspData) {
        CiDto configurationItem=new CiDto();
//        if(nspData.get("relatedCI")!=null){
//            configurationItem.setRelatedCI((Map<String, List<CiDto.RelatedCI>>)nspData.get("relatedCI"));
//        }else if(nspData.get("edgeFullUpdate")!=null){
//            configurationItem.setEdgeFullUpdate((Boolean) nspData.get("edgeFullUpdate"));
//        }else if(nspData.get("orUpdate")!=null){
//            configurationItem.setOrUpdate((Boolean) nspData.get("orUpdate"));
//        }
        configurationItem.setProperties(nspData);
        Result<String> createResult = cmdbService.createCI(nspTable, configurationItem);
        return createResult;
    }

    /**
     * 更新设备
     * @param nspTable
     * @param ciCode
     * @param nspData
     */
    private Result<Void> updateCI(String nspTable, String ciCode, LinkedHashMap<String, Object> nspData) {
        PropertiesDto propertiesDto=new PropertiesDto();
        propertiesDto.setProperties(nspData);
        Result<Void> updateResult = cmdbService.updateCI(nspTable, ciCode, propertiesDto);
        return updateResult;
    }
    /**
     * 将nspData写入CMDB
     * @param nspNopItem
     * @param nspDatas
     * @return
     * @throws Exception
     */
    private HashMap<String,Integer> writeToNspCMDB1(NspNopItem nspNopItem, List<LinkedHashMap<String, Object>> nspDatas) throws Exception {
        String nspTable = nspNopItem.getNspTable();
        //先判断是否存在
        Integer updateNum=0;
        Integer insertNum=0;
        List<String> errMessage=new ArrayList<>();

        for (LinkedHashMap<String, Object> nspData : nspDatas) {
            String ciCode = (String) nspData.get("nop_ciCode");
            CISearchDto ciSearchDto=new CISearchDto();
            Map<String,Object> map=new HashMap<>();
            map.put("nop_ciCode",ciCode);
            ciSearchDto.setExactFilters(map);
            Result<PageInfo> infoResult = cmdbService.listCI(nspTable, ciSearchDto);
            System.out.println(infoResult);
            //Result<CiDto> result = cmdbService.findCIById(nspTable, ciCode, 1, false, false);
            if(infoResult.getCode()==-1){
                throw new Exception("传递的参数有误！");
            }
            if(infoResult.getCode()==0&&infoResult.getRes().getTotal()==0){
                //新建设备
                Result<String> createResult = createCI(nspTable, nspData);
                if(createResult.getCode()==0){
                    log.info("新增"+nspTable+"成功");
                    insertNum++;
                }else {
                    log.info("新增"+nspTable+"失败,"+createResult.getError());
                    String errString="新增"+nspTable+"失败,"+createResult.getError();
                    errMessage.add(errString);
                    //throw new InfoException("新增"+nspTable+"失败",insertNum,updateNum);
                    //throw new Exception("新增"+nspTable+"失败");
                }
            }else {
                LinkedHashMap<String,Object> linkedHashMap=(LinkedHashMap<String,Object>)infoResult.getRes().getList().get(0);
                String id = (String)linkedHashMap.get("id");
                //更新设备
                Result<Void> updateResult = updateCI(nspTable,id, nspData);
                if(updateResult.getCode()==0){
                    log.info("更新"+nspTable+"成功");
                    updateNum++;
                }else {
                    log.info("更新"+nspTable+"失败,"+updateResult.getError());
                    String errString="更新"+nspTable+"失败,"+updateResult.getError();
                    errMessage.add(errString);
                    //throw new Exception("更新"+nspTable+"失败");
                }
            }
        }
        HashMap<String,Integer> numHashMap=new HashMap<>();
        numHashMap.put("updateNum",updateNum);
        numHashMap.put("insertNum",insertNum);
        if(errMessage!=null&&errMessage.size()>0){
            throw new Exception(errMessage.toString());
        }
        return numHashMap;
    }

}