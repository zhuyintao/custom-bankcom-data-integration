package com.eccom.cloudnet.custom.bankcom.dataintegration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop.CISearchDto;
import com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop.PageInfo;
import com.eccom.cloudnet.custom.bankcom.dataintegration.dto.nop.Result;
import com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop.NspNopItem;
import com.eccom.cloudnet.custom.bankcom.dataintegration.service.feign.CMDBService;
import com.eccom.cloudnet.custom.bankcom.dataintegration.service.nop.NopCmdbService;
import com.eccom.cloudnet.custom.bankcom.dataintegration.service.nop.RpcService;
import com.eccom.cloudnet.custom.bankcom.dataintegration.utils.ToClass;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataIntegrationApplicationTests {
    @Autowired
    private NopCmdbService nopCmdbService;
    @Autowired
    private CMDBService cmdbService;

//    @Test
//    public void f1(){
//        CISearchDto ciSearchDto=new CISearchDto();
//        Map<String,Object> hashMap=new HashMap<>();
//        hashMap.put("nop_ciCode","1");
//        ciSearchDto.setExactFilters(hashMap);
//        Result<PageInfo> infoResult = cmdbService.listCI("Router", ciSearchDto);
//        System.out.println(infoResult);
//    }
//    @Test
//    public void f(){
//        List<List<Object>> lists1=new ArrayList<>();
//        List<Object> list1=new ArrayList<>();
//        list1.add("i_id");
//        list1.add("=");
//        list1.add(1);
//        lists1.add(list1);
//        Gson gson = new Gson();
//        String s1 = gson.toJson(lists1);
//        int i = RpcService.unicodetoString(JSON.toJSONString(s1)).indexOf("?");
//        int length = RpcService.unicodetoString(JSON.toJSONString(s1)).split("=").length;
//        System.out.println(length);
//
//        //String[] split = s2.split("=");
//
//        // List<List> lists = JSON.parseArray(s1, List.class);
//       // System.out.println(s2);
//        System.out.println(s1);
//    }
//    @Test
//    public void e() throws Exception {
//        List<LinkedHashMap<String, Object>> nopDatas = nopCmdbService.getNopDatas("Switch");
//        System.out.println(nopDatas);
//    }
//    @Test
//    public void c() throws Exception {
//        int connectForUid = RpcService.getConnectForUid();
//        System.out.println(connectForUid);
//        //('i_id', '=', 1)
//        List<List<Object>> lists1=new ArrayList<>();
//        List<Object> list1=new ArrayList<>();
//        list1.add("i_id");
//        list1.add("=");
//        list1.add(1);
//        lists1.add(list1);
//        Gson gson = new Gson();
//        Object data = RpcService.getIds(connectForUid, gson.toJson(lists1));
//        //String rspStr = gson.toJson(data);
//        //System.out.println(rspStr);
//        List<List<Object>> lists2=new ArrayList<>();
//        List<Object> list2=new ArrayList<>();
//        list2.add("id");
//        list2.add("in");
//        list2.add(data);
//        lists2.add(list2);
//        Object data1 = RpcService.getDatas(connectForUid, gson.toJson(lists2));
//        System.out.println("解码前数据："+data1);
//        String s = RpcService.unicodetoString(data1.toString());
//        //gson.toJson(data1, Map.class);
//        System.out.println("解码后数据："+s);
//        List<LinkedHashMap> maps = JSON.parseArray(s, LinkedHashMap.class);
//        List <LinkedHashMap<String,Object>> lists=new ArrayList<>();
//        for (LinkedHashMap map : maps) {
//            LinkedHashMap<String,Object> hashMap=(LinkedHashMap<String, Object>)map;
//            lists.add(hashMap);
//        }
//        System.out.println("lists:"+lists.size());
//    }
//    @Test
//    public void d() throws Exception {
//        List<List<Object>> lists2=new ArrayList<>();
//        List<Object> list2=new ArrayList<>();
//        list2.add("id");
//        list2.add("in");
//        list2.add(1300576);
//        lists2.add(list2);
//        Gson gson = new Gson();
//        Object data1 = RpcService.getDatas(1, gson.toJson(lists2));
//        System.out.println(gson.toJson(data1));
//    }
    @Test
    public void a() throws Exception {
//        LinkedList<LinkedHashMap<String,Object>> nopLinkedList=new LinkedList<>();
//        LinkedHashMap<String, Object> linkedHashMap1=new LinkedHashMap<>();
//        linkedHashMap1.put("name","设备1");
//        linkedHashMap1.put("dev_Type","switch");
//        linkedHashMap1.put("ip","1.10.1.1");
//        LinkedHashMap<String, Object> linkedHashMap2=new LinkedHashMap<>();
//        linkedHashMap2.put("name","设备2");
//        linkedHashMap2.put("dev_Type","switch2");
//        linkedHashMap2.put("ip","2.10.2.2");
//        nopLinkedList.add(linkedHashMap1);
//        nopLinkedList.add(linkedHashMap2);
//        nopCmdbService.syncData(nopLinkedList);
        //System.out.println(nspNopMaps);
    }
//    @Test
//    public void b() throws IOException {
//        LinkedHashMap<String, Object> linkedHashMap=new LinkedHashMap<>();
//        //{name:"设备1",ip:"1.10.1.1",dev_Type:"交换机"}，
//        //{name:"设备2",ip:"1.10.1.2",dev_Type:"防火墙"}，
//        linkedHashMap.put("name","设备1");
//        linkedHashMap.put("dev_Type","switch");
//        linkedHashMap.put("ip","1.10.1.1");
//        nopCmdbService.syncData(linkedHashMap);
//    }
    @Test
    public void b() throws Exception {
        String s1="同步起始时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //测试Router路由设备
        List<Result> resultList = nopCmdbService.syncData();
        System.out.println(s1);
        System.out.println("同步结束时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(resultList);
    }
//    @Test
//    public void b1(){
//        LinkedHashMap<String,Object> nspDatasLinkedHashMap=new LinkedHashMap<>();
//
//        CISearchDto ciSearchDto=new CISearchDto();
//        Result<PageInfo> infoResult = cmdbService.listCI("LoadBalancer", ciSearchDto);
//        System.out.println(infoResult);
//        List<LinkedHashMap<String,Object>> lists = infoResult.getRes().getList();
//        for (LinkedHashMap<String, Object> hashMap : lists) {
//            LinkedHashMap<String,Object> propertiesLinkedHashMap = (LinkedHashMap<String,Object>)hashMap.get("properties");
//            String nop_ciCode =(String) propertiesLinkedHashMap.get("nop_ciCode");
//            propertiesLinkedHashMap.put("id",hashMap.get("id"));
//            nspDatasLinkedHashMap.put(nop_ciCode,propertiesLinkedHashMap);
//        }
//        System.out.println(nspDatasLinkedHashMap);
//    }
    @Test
    public void b1(){

        Object object=b2();
        List<HashMap> castList = ToClass.castList(object, HashMap.class);
        //LinkedHashMap<String,Object> linkedHashMap=new LinkedHashMap<>();
        for (HashMap hashMap : castList) {
            HashMap<String,Object> hashMap1=(HashMap<String,Object>)hashMap.get("code");

            Collection<Object> values = hashMap1.values();
            //hashMap.put("code",hashMap.get("code").)
        }
        System.out.println(castList);

    }

    private Object b2() {
        HashMap<String,Object> hashMap01=new HashMap<>();
        hashMap01.put("1","aaa");
        hashMap01.put("2","bbb");
        HashMap<String,Object> hashMap02=new HashMap<>();
        hashMap02.put("3",new HashMap<>());
        hashMap02.put("4","ddd");
        List<HashMap<String,Object>> list=new ArrayList<>();
        list.add(hashMap01);
        list.add(hashMap02);
        return list;
    }
}