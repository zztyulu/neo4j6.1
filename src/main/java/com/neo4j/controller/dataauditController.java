package com.neo4j.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo4j.domain.base.TableRecordsChecking.TRCField;
import com.neo4j.domain.base.TableRecordsChecking.TRCReturnSource;
import com.neo4j.domain.base.TableStructureChecking.DataLD;
import com.neo4j.domain.base.TableStructureChecking.Field;
import com.neo4j.domain.base.TableStructureChecking.ReturnSource;
import com.neo4j.domain.base.abs.*;
import com.neo4j.domain.base.fieldIntegrityChecking.FICAfferentParameter;
import com.neo4j.domain.base.fieldIntegrityChecking.FICReturnSource;
import com.neo4j.domain.base.tablenumchecking.Msg;
import com.neo4j.domain.base.abs.DataAudit.SourceCollection;
import com.neo4j.test.RestfulHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @program:  enginegraph
 * @description:  数据审计
 * @author: liuchenyang
 * @create: 2019-06-23 15.49
 **/

@RequestMapping("/DataAudit")
@RestController
public class dataauditController {
   //0.0.0.0  192.168.0.101
    private  String URL_4_1 = "http://127.0.0.1:5501/data-audit/table-num-checking/";
    private  String URL_4_2 = "http://127.0.0.1:5501/data-audit/table-structure-checking/";
    private  String URL_4_3 = "http://127.0.0.1:5501/data-audit/table-records-checking/";
    private  String URL_4_4 = "http://127.0.0.1:5501/data-audit/field-integrity-checking/";

 /*
*  4.1表数一致性校验  TableNumberConsistencyCheck
* */
    @RequestMapping(value = "/TableNumberConsistencyCheck")
    public Object TableNumberConsistencyCheck (@RequestBody SourceCollection sourceCollection)
    {
        System.out.println("进入");
        Response response1=new Response();
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(URL_4_1);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");

        StringEntity requestJson ;
        try {
            requestJson = new StringEntity(mapper.writeValueAsString(sourceCollection), "utf-8");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("字符串解析异常");
            response1.setCode(406);   //字符串解析异常
            return response1;
        }
        // StringEntity requestJson = new StringEntity(, "utf-8");
        requestJson.setContentType("application/json");
        request.setEntity(requestJson);
        try {
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文
            JSONObject jsonObject = JSONObject.parseObject(result);
            //state：状态标识，为1表示正常返回；为0表示出错；
            int state = (int) jsonObject.get("state");
            if(state==0)
            {
                //state：状态标识，为1表示正常返回；为0表示出错；
                // msg：结果信息，当状态标识为1时，为结果详情，当状态标识为0时，为错误信息String
                response1.setCode(501);
                String error = jsonObject.getJSONObject("msg").toString();
                response1.setObject(error);
                return response1;
            }else if(state==1)
            {
                String jsonObject1 = jsonObject.getJSONObject("msg").toJSONString();
                Msg msg=  mapper.readValue(jsonObject1, Msg.class);

                response1.setCode(200);
                response1.setObject(msg);
                return response1;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            response1.setCode(400);
            return response1;
        }
        return response1;
    }

    /*
     *  4.2表结构一致性校验  TableStructureChecking
     * */
    @RequestMapping(value = "/TableStructureChecking")
    public Object TableStructureChecking (@RequestBody SourceCollection sourceCollection)
    {
        System.out.println("进入4.2");
        Response response1=new Response();
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(URL_4_2);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");

        StringEntity requestJson ;
        try {
            requestJson = new StringEntity(mapper.writeValueAsString(sourceCollection), "utf-8");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("字符串解析异常");
            response1.setCode(406);   //字符串解析异常
            return response1;
        }
        // StringEntity requestJson = new StringEntity(, "utf-8");
        requestJson.setContentType("application/json");
        request.setEntity(requestJson);
        try {
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文
            JSONObject jsonObject = JSONObject.parseObject(result);
            //state：状态标识，为1表示正常返回；为0表示出错；
            int state = (int) jsonObject.get("state");
            if(state==0)
            {
                //state：状态标识，为1表示正常返回；为0表示出错；
                response1.setCode(501);
                String error = jsonObject.getJSONObject("msg").toString();
                response1.setObject(error);
                return response1;
            }else if(state==1)
            {
             //   String jsonObject1 = jsonObject.getJSONObject("msg").toJSONString();
             //   Msg msg=  mapper.readValue(jsonObject1, Msg.class);
                JSONObject msg= jsonObject.getJSONObject("msg");
                List<ReturnSource> returnSourceList=new ArrayList<>();
                Map<String,Object> map= msg.getInnerMap();
                Set<String> set = map.keySet();

               for(Iterator<String> iterator=set.iterator(); iterator.hasNext();)
               {
                   ReturnSource NewreturnSource=new ReturnSource();
                   JSONObject jsonObject1=(JSONObject)map.get(iterator.next());
                   //   JSONArray 转List  same_field
                   JSONArray same_field = jsonObject1.getJSONArray("same_field");
                   List<String> same_fieldlist= JSON.parseObject(same_field.toJSONString(), new TypeReference<List<String>>() {});
                   NewreturnSource.setSame_field(same_fieldlist);

                   //   JSONArray 转List  new_in_1
                   JSONArray new_in_1 = jsonObject1.getJSONArray("new_in_1");
                   List<String> new_in_1list= JSON.parseObject(new_in_1.toJSONString(), new TypeReference<List<String>>() {});
                   NewreturnSource.setNew_in_1(new_in_1list);

                   //   JSONArray 转List  new_in_2
                   JSONArray new_in_2 = jsonObject1.getJSONArray("new_in_2");
                   List<String> new_in_2list= JSON.parseObject(new_in_2.toJSONString(), new TypeReference<List<String>>() {});
                   NewreturnSource.setNew_in_2(new_in_2list);

                   // jsonboject 转      data_type_diff
                   JSONObject data_type_diffJO=jsonObject1.getJSONObject("data_type_diff");
                   Map<String,Object> mapDY= data_type_diffJO.getInnerMap();
                   Set<String> setDY = mapDY.keySet();
                   List<Field> fieldList=new ArrayList<>();

                   for(Iterator<String> iteratorDY=setDY.iterator(); iteratorDY.hasNext();)
                   {
                       String key= iteratorDY.next();    //   mapDY.get(key) 类型是 JSONArray
                       Field field=new Field();
                       field.setName(key);
                       JSONArray jsonArrayDY= (JSONArray)mapDY.get(key);
                       List<String> fieldlistDY= JSON.parseObject(jsonArrayDY.toJSONString(), new TypeReference<List<String>>() {});
                       field.setType(fieldlistDY);
                       fieldList.add(field);
                   }
                   NewreturnSource.setData_type_diff(fieldList);

                   // jsonboject 转      data_length_diff
                   JSONObject data_length_diffDL=jsonObject1.getJSONObject("data_length_diff");
                   Map<String,Object> mapDL= data_length_diffDL.getInnerMap();
                   Set<String> setDL = mapDL.keySet();
                   List<DataLD> dataLDList=new ArrayList<>();

                   for(Iterator<String> iteratorDL=setDL.iterator(); iteratorDL.hasNext();)
                   {
                       String key= iteratorDL.next();
                       DataLD dataLD=new DataLD();
                       dataLD.setName( key);
                       JSONArray jsonArrayDL= (JSONArray)mapDL.get(key);
                       List<Integer> fieldlistDL= JSON.parseObject(jsonArrayDL.toJSONString(), new TypeReference<List<Integer>>() {});
                       dataLD.setInteger(fieldlistDL);
                       dataLDList.add(dataLD);
                   }
                   NewreturnSource.setData_length_diff(dataLDList);

                   returnSourceList.add(NewreturnSource);
               }
/*
* 成功返回的是一个List
* */
                response1.setCode(200);
                response1.setObject(returnSourceList);
                return response1;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            response1.setCode(400);
            return response1;
        }
        return response1;
    }

    /*
     *  4.3表记录一致性校验  TableRecordsChecking
     * */
    @RequestMapping(value = "/TableRecordsChecking")
    public Object  TableRecordsChecking (@RequestBody SourceCollection sourceCollection)
    {
        System.out.println("进入4.3");
        Response response1=new Response();
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(URL_4_3);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");
        StringEntity requestJson ;
        try {
            requestJson = new StringEntity(mapper.writeValueAsString(sourceCollection), "utf-8");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("字符串解析异常");
            response1.setCode(406);   //字符串解析异常
            return response1;
        }
        // StringEntity requestJson = new StringEntity(, "utf-8");
        requestJson.setContentType("application/json");
        request.setEntity(requestJson);   //传参
        try {
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文
            JSONObject jsonObject = JSONObject.parseObject(result);
            //state：状态标识，为1表示正常返回；为0表示出错；
            int state = (int) jsonObject.get("state");
            if(state==0)
            {
                //state：状态标识，为1表示正常返回；为0表示出错；
                response1.setCode(501);
                String error = jsonObject.getJSONObject("msg").toString();
                response1.setObject(error);
                return response1;
            }else if(state==1)
            {
                //   String jsonObject1 = jsonObject.getJSONObject("msg").toJSONString();
                //   Msg msg=  mapper.readValue(jsonObject1, Msg.class);
                JSONObject msg= jsonObject.getJSONObject("msg");
                List<TRCReturnSource> trcReturnSourceList=new ArrayList<>();
                Map<String,Object> map= msg.getInnerMap();      //遍历
                Set<String> set = map.keySet();

                for(Iterator<String> iterator=set.iterator(); iterator.hasNext();)
                {
                   TRCReturnSource trcReturnSource=new TRCReturnSource();
                    JSONObject jsonObject1=(JSONObject)map.get(iterator.next());
                    //   JSONArray 转List  same_field
                    JSONArray same_field = jsonObject1.getJSONArray("same_field");
                    List<String> same_fieldlist= JSON.parseObject(same_field.toJSONString(), new TypeReference<List<String>>() {});
                    trcReturnSource.setSame_field(same_fieldlist);

                    //   JSONArray 转List  diff_field
                    JSONArray diff_field = jsonObject1.getJSONArray("diff_field");
                    List<String> diff_fieldlist= JSON.parseObject(diff_field.toJSONString(), new TypeReference<List<String>>() {});
                    trcReturnSource.setDiff_field(diff_fieldlist);

                    // jsonboject 转      in_table1
                    JSONObject  in_table1JO=jsonObject1.getJSONObject("in_table1");
                    Map<String,Object> in_table1Map= in_table1JO.getInnerMap();
                    Set<String> seti1 = in_table1Map.keySet();
                    List<TRCField> trcfieldList=new ArrayList<>();

                    for(Iterator<String> iteratori1=seti1.iterator(); iteratori1.hasNext();)
                    {
                        String key= iteratori1.next();    //   mapi1.get(key) 类型是 JSONArray
                        JSONArray jsonArrayi1= (JSONArray)in_table1Map.get(key);
                        List<Object> fieldlisti1= JSON.parseObject(jsonArrayi1.toJSONString(), new TypeReference<List<Object>>() {});
                        TRCField trcField=new TRCField();
                        trcField.setName(key);
                        trcField.setValues(fieldlisti1);
                        trcfieldList.add(trcField);
                    }
                    trcReturnSource.setIn_table1(trcfieldList);

                    // jsonboject 转      in_table2
                    JSONObject  in_table2JO=jsonObject1.getJSONObject("in_table2");
                    Map<String,Object>  in_table2Map= in_table2JO.getInnerMap();
                    Set<String> seti2 = in_table2Map.keySet();
                    List<TRCField> trcfieldList2=new ArrayList<>();

                    for(Iterator<String> iteratori2=seti2.iterator(); iteratori2.hasNext();)
                    {
                        String key= iteratori2.next();    //   mapi1.get(key) 类型是 JSONArray
                        JSONArray jsonArrayi2= (JSONArray)in_table2Map.get(key);
                        List<Object> fieldlisti2= JSON.parseObject(jsonArrayi2.toJSONString(), new TypeReference<List<Object>>() {});
                        TRCField trcField=new TRCField();
                        trcField.setName(key);
                        trcField.setValues(fieldlisti2);
                        trcfieldList2.add(trcField);
                    }
                    trcReturnSource.setIn_table2(trcfieldList2);

                    trcReturnSourceList.add(trcReturnSource);
                }
                /*
                 * 成功返回的是一个List
                 * */
                response1.setCode(200);
                response1.setObject(trcReturnSourceList);
                return response1;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            response1.setCode(400);
            return response1;
        }
        return response1;
    }


    /*
     *  4.4数据合规性校验  field-integrity-checking
     * */
    @RequestMapping(value = "/FieldIntegrityChecking")
    public Object FieldIntegrityChecking (@RequestBody FICAfferentParameter ficAfferentParameter)
    {
        System.out.println("进入4.4");
        Response response1=new Response();
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(URL_4_4);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");

        StringEntity requestJson ;
        try {
            requestJson = new StringEntity(mapper.writeValueAsString(ficAfferentParameter), "utf-8");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("字符串解析异常");
            response1.setCode(406);   //字符串解析异常
            response1.setObject("字符串解析异常");
            return response1;
        }
        // StringEntity requestJson = new StringEntity(, "utf-8");
        requestJson.setContentType("application/json");
        request.setEntity(requestJson);
        try {
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文
            JSONObject jsonObject = JSONObject.parseObject(result);
            //state：状态标识，为1表示正常返回；为0表示出错；
            int state = (int) jsonObject.get("state");
            if(state==0)
            {
                //state：状态标识，为1表示正常返回；为0表示出错；
                // msg：结果信息，当状态标识为1时，为结果详情，当状态标识为0时，为错误信息String
                response1.setCode(501);
                String error = jsonObject.getJSONObject("msg").toString();
                response1.setObject(error);
                return response1;
            }else if(state==1)
            {
                Integer table_name_length =(Integer)jsonObject.get("table_name_length");
                Integer field_content_length= (Integer)jsonObject.get("field_content_length");
                Integer special_chars= (Integer)jsonObject.get("special_chars");
                Integer field_content_empty= (Integer)jsonObject.get("field_content_empty");
                FICReturnSource ficReturnSource=new FICReturnSource();
                ficReturnSource.setTable_name_length(table_name_length);
                ficReturnSource.setField_content_length(field_content_length);
                ficReturnSource.setSpecial_chars(special_chars);
                ficReturnSource.setField_content_empty(field_content_empty);
                response1.setCode(200);
                response1.setObject(ficReturnSource);
                return response1;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            response1.setCode(400);
            e.getMessage();
            response1.setObject("信息填写错误，请检查");
            return response1;
        }

        response1.setCode(400);
        return response1;
    }







}
