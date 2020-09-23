package com.eccom.cloudnet.custom.bankcom.dataintegration.service.nop;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.eccom.cloudnet.custom.bankcom.dataintegration.config.nop.RpcConfig;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RpcService {
    public static String URL;
    public static String DB;
    public static String USERNAME;
    public static String PASSWORD;
    public static String MODULEIDS;
    public static String METHODIDS;
    public static String MODULEDATAS;
    public static String METHODDATAS;
    public static Boolean False=false;
    public static Boolean True=true;
    public static void setConfigInfo(RpcConfig rpcConfig){
        RpcService.URL=rpcConfig.getUrl();
        RpcService.DB=rpcConfig.getDb();
        RpcService.USERNAME=rpcConfig.getUserName();
        RpcService.PASSWORD=rpcConfig.getPassWord();
        RpcService.MODULEIDS=rpcConfig.getModuleIds();
        RpcService.METHODIDS=rpcConfig.getMethodIds();
        RpcService.MODULEDATAS=rpcConfig.getModuleDatas();
        RpcService.METHODDATAS=rpcConfig.getMethodDatas();
    }
    //rpc接口登入
    public static int getConnectForUid() throws Exception {
        XmlRpcClient xmlRpcLogin = new XmlRpcClient();
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setEnabledForExceptions(true);
        config.setServerURL(new URL(URL + "/xmlrpc/common"));
        xmlRpcLogin.setConfig(config);
        try {
            Object[] params = new Object[] { DB, USERNAME, PASSWORD };
            Object id = xmlRpcLogin.execute("login", params);
            if (id instanceof Integer)
                return (Integer) id;
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    //请求rpc,获取数据
    public static Object getIds(int uid, String jsonParams) throws Exception {
        XmlRpcClient clientRqst = new XmlRpcClient();
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setEnabledForExceptions(true);
        config.setServerURL(new URL(URL + "/xmlrpc/object"));
        clientRqst.setConfig(config);
        //TODO
        try {
            Gson gson = new Gson();
            Object[] params = new Object[] {DB, uid, PASSWORD, MODULEIDS, METHODIDS, gson.fromJson(jsonParams, List.class)};
            //Object[] params = new Object[] {uid,gson.fromJson(jsonParams, List.class)};
            System.out.println(gson.toJson(params));
            Object clientRsp = clientRqst.execute("execute", params);
            return clientRsp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Object getDatas(int uid, String jsonParams) throws Exception {
        XmlRpcClient clientRqst = new XmlRpcClient();
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setEnabledForExceptions(true);
        config.setServerURL(new URL(URL + "/xmlrpc/object"));
        clientRqst.setConfig(config);
        try {
            Gson gson = new Gson();
            List fieldList=new ArrayList();
            Collections.addAll(fieldList,"name","TYPE","MANAGEIP","code","FACTORY","PLATFORM","SERIES","SEQUENCE","OS");
           // Object fields = JSON.toJSON(fieldList);
            String fields = gson.toJson(fieldList);

            Object[] params = new Object[] {DB, uid, PASSWORD, MODULEDATAS, METHODDATAS, gson.fromJson(jsonParams, List.class),gson.fromJson(fields, List.class)};
            System.out.println(params);
            Object clientRsp = clientRqst.execute("execute",params);
            return clientRsp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //请求rpc,获取数据
//    public static Object getData1(int uid, String jsonParams) throws Exception {
//        XmlRpcClient clientRqst = new XmlRpcClient();
//        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
//        config.setEnabledForExceptions(true);
//        config.setServerURL(new URL(URL + "/xmlrpc/object"));
//        clientRqst.setConfig(config);
//        try {
//            Gson gson = new Gson();
//            Object[] params = new Object[] {"wedoapp", uid, "nops2016**", "worker.rpc", "search_ci_fetchall", gson.fromJson(jsonParams, List.class),"*",False,True};
//            System.out.println(gson.toJson(params));
//            Object clientRsp = clientRqst.execute("execute", params);
//            return clientRsp;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    //请求rpc接口
//    public static boolean getMap(Map<String, Object> item) throws Exception {
//        System.out.println(item);
//        int uid = getConnectForUid();
//        System.out.println(uid);
//        if (uid != -1) {
//            Gson gson = new Gson();
//            String params = gson.toJson(item);
//            Object rpcRspObject = getData(uid, params);
//            System.out.println(gson.toJson(rpcRspObject));
//            if (rpcRspObject != null) {
//                String rspStr = gson.toJson(rpcRspObject);
//                System.out.println(rspStr);
//                //写入返回信息
//                //writeFile("rpc.log",rspStr);
//            } else {
//                System.out.println("请求rpc接口异常");
//                return false;
//            }
//        }else {
//            System.out.println("获取uid异常");
//            return false;
//        }
//        return true;
//    }
    public static String unicodetoString(String str) {
        Charset set = Charset.forName("UTF-16");
        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher m = p.matcher(str);
        int start = 0;
        int start2 = 0;
        StringBuffer sb = new StringBuffer();
        while (m.find(start)) {
            start2 = m.start();
            if (start2 > start) {
                String seg = str.substring(start, start2);
                sb.append(seg);
            }
            String code = m.group(1);
            int i = Integer.valueOf(code, 16);
            byte[] bb = new byte[4];
            bb[0] = (byte) ((i >> 8) & 0xFF);
            bb[1] = (byte) (i & 0xFF);
            ByteBuffer b = ByteBuffer.wrap(bb);
            sb.append(String.valueOf(set.decode(b)).trim());
            start = m.end();
        }
        start2 = str.length();
        if (start2 > start) {
            String seg = str.substring(start, start2);
            sb.append(seg);
        }
        return sb.toString();
    }
}
