package com.neo4j.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo4j.domain.base.DataManagement.BSReturnSource;
import com.neo4j.domain.base.DataManagement.SCReturnSource;
import com.neo4j.domain.base.abs.Response;
import com.neo4j.test.RestfulHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @program:  enginegraph
 * @description:  数据资源管理
 * @author: liuchenyang
 * @create: 2019-06-25 15.49
 **/

@RequestMapping("/DataManage")
@RestController
public class DataManageController {
    private  String url= "http://127.0.0.1:7500";
    private  String URL_3_1 = url+"/datasource/save";
    private  String URL_3_2 =  url+"/datasource/delete";
    private  String URL_3_3 =  url+"/businessmodel/save";
    private  String URL_3_4 =  url+"/businessmodel/deleteModel";
    private  String URL_3_5 =  url+"/sensitiveInit/effect";
    private  String URL_3_6 =  url+"/sensitive/findChanged";
    private  String URL_3_7 =  url+"/sensitive/compareVersion";
    /*
     *  3.1 新增数据源  /datasource/save
     *  数据库变化：本功能调用成功后，在pd_datasource表中插入一条数据，表示新增加了一个数据源。
     * */
    @RequestMapping(value = "/DatasourceSave")
    public Object DatasourceSave (@RequestParam("ds")String ds)
    {
        System.out.println("进入");
        Response response1=new Response();
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(URL_3_1);
        // 设置1个post参数，一个是ds
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("ds", ds));
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(parameters);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("字符串解析异常");
            response1.setCode(406);   //字符串解析异常
            return response1;
        }
        // 将请求实体设置到httpPost对象中
         request.setEntity(formEntity);
        try {
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文
            JSONObject jsonObject = JSONObject.parseObject(result);
            //state：状态标识，为1表示正常返回；为0表示出错；
            response1.setCode(200);
            response1.setObject("success");
            return response1;

        }catch (Exception e)
        {
            e.printStackTrace();
            response1.setCode(400);
            return response1;
        }
    }
    /*
     *  3.2 新增数据源  /datasource/delete
     *  数据库变化：本功能调用成功后，在pf_datasource表中删除了一条数据，表示删除了一个数据源。
     * */
    @RequestMapping(value = "/DatasourceDelete")
    public Object DatasourceDelete (@RequestParam("dsIds")String dsIds)
    {
        System.out.println("进入");
        Response response1=new Response();
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(URL_3_2);
        // 设置1个post参数，一个是ds
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("dsIds", dsIds));
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(parameters);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("字符串解析异常");
            response1.setCode(406);   //字符串解析异常
            return response1;
        }
        // 将请求实体设置到httpPost对象中
        request.setEntity(formEntity);
        try {
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文
            JSONObject jsonObject = JSONObject.parseObject(result);
            //state：状态标识，为1表示正常返回；为0表示出错；
            response1.setCode(200);
            response1.setObject("success");
            return response1;

        }catch (Exception e)
        {
            e.printStackTrace();
            response1.setCode(400);
            return response1;
        }
    }

    /*
     *  3.3 新增模型  127.0.0.1:7500/businessmodel/save
     *  数据库变化：本功能调用成功后，在pf_businessmodel_model表中插入了一条数据，表示增加了一个模型；
     *  此外，还会在pf_businessmodel_version表中插入一条数据，表示第一次生成的模型为其第一个版本。
     * */
    @RequestMapping(value = "/BusinessmodelSave")
    public Object BusinessmodelSave (@RequestParam("ds")String ds)
    {
        System.out.println("进入3.3");
        Response response1=new Response();
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(URL_3_3);
        // 设置1个post参数，一个是ds
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("ds", ds));
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(parameters);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("字符串解析异常");
            response1.setCode(406);   //字符串解析异常
            return response1;
        }
        // 将请求实体设置到httpPost对象中
        request.setEntity(formEntity);
        try {
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文
            JSONObject jsonObject = JSONObject.parseObject(result);
            //success=true 返回正常数据  =false 返名称重复
            boolean success = (Boolean) jsonObject.get("success");
            if(success)
            {
                response1.setCode(200);
                String jsonObject1 = jsonObject.getJSONObject("model").toJSONString();
                BSReturnSource bsReturnSource=  mapper.readValue(jsonObject1, BSReturnSource.class);
                response1.setObject(bsReturnSource);
                return response1;
            }
            response1.setCode(409);
            response1.setObject("名称重复");
            return response1;

        }catch (Exception e)
        {
            e.printStackTrace();
            response1.setCode(406);
            response1.setObject("字符串解析异常");
            return response1;
        }
    }

    /*
     *  3.4 删除模型  127.0.0.1:7500/businessmodel/deleteModel
     *
     *  参数示例：
        modelId: fbc04597919a45c290d31f02948f6b36
        modelType: dbmx
        参数说明：
        modelId 要删除的模型的id
        modelType 固定值，无需更改
     *
     *  数据库变化：在pf_businessmodel_model表中会删除对应的行，且图数据库中的数据也一并被删除。
     * */
    @RequestMapping(value = "/BusinessmodelDeleteModel")
    public Object BusinessmodelDeleteModel (@RequestParam("modelId")String modelId, @RequestParam("modelType")String modelType)
    {
        modelType="dbmx";
        System.out.println("进入3.4");
        Response response1=new Response();
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(URL_3_4);
        // 设置1个post参数，一个是ds
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("modelId", modelId));
        parameters.add(new BasicNameValuePair("modelType", modelType));
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(parameters);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("字符串解析异常");
            response1.setCode(406);   //字符串解析异常
            return response1;
        }
        // 将请求实体设置到httpPost对象中
        request.setEntity(formEntity);
        try {
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文
            JSONObject jsonObject = JSONObject.parseObject(result);
            //STATUS  SUCCESS  FAILURE
            String  STATUS = (String) jsonObject.get("STATUS");
            if(STATUS.equals("SUCCESS"))
            {
                response1.setCode(200);
                response1.setObject("SUCCESS");
                return response1;
            }
            response1.setCode(400);
            response1.setObject("失败");
            return response1;

        }catch (Exception e)
        {
            e.printStackTrace();
            response1.setCode(406);
            response1.setObject("字符串解析异常");
            return response1;
        }
    }

    /*
     *  3.5 版本生效（同步到图数据库）  127.0.0.1:7500/sensitiveInit/effect
        参数说明： 表 pf_businessmodel_version
       businessModelId: 要生效的模型的id   表 pf_businessmodel_version的 businessmodelid
       businessVersionId: 要生效的模型的版本id       表 pf_businessmodel_version的id
     *
     *  数据库变化：本功能调用成功后，pf_businessmodel_version表中对应生效版本的行中的status的值变为2，表示当前版本为生效版本。
     * */
    @RequestMapping(value = "/SensitiveInitEffect")
    public Object SensitiveInitEffect (@RequestParam("businessModelId")String businessModelId, @RequestParam("businessVersionId")String businessVersionId)
    {
        System.out.println("进入3.5");
        Response response1=new Response();
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(URL_3_5);
        // 设置1个post参数，一个是ds
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("businessModelId", businessModelId));
        parameters.add(new BasicNameValuePair("businessVersionId", businessVersionId));
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(parameters);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("字符串解析异常");
            response1.setCode(406);   //字符串解析异常
            return response1;
        }
        // 将请求实体设置到httpPost对象中
        request.setEntity(formEntity);
        try {
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文
            JSONObject jsonObject = JSONObject.parseObject(result);
            //STATUS  SUCCESS  FAILURE
            String  STATUS = (String) jsonObject.get("STATUS");
            if(STATUS.equals("SUCCESS"))
            {
                response1.setCode(200);
                Object time = jsonObject.get("time");  //time为图谱化模型所花费的时间，可以作为数据转换的效率。
                response1.setObject(time);
                return response1;
            }
            response1.setCode(400);
            String  MSG = (String) jsonObject.get("MSG");
            response1.setObject(MSG);
            return response1;

        }catch (Exception e)
        {
            e.printStackTrace();
            response1.setCode(406);
            response1.setObject("字符串解析异常");
            return response1;
        }
    }

    /*
      *  3.6 生成新版本  127.0.0.1:7500/sensitive/findChanged
         参数说明： 表 pf_businessmodel_version
        businessModelId: 当前已生效版本对应的模型id   表 pf_businessmodel_version的 businessmodelid
        businessVersionId: 当前已生效的版本       表 pf_businessmodel_version的id
      *
      *  数据库变化：本功能调用成功后，pf_businessmodel_version表中对应生效版本的行中的status的值变为2，表示当前版本为生效版本。
      * */
    @RequestMapping(value = "/SensitiveFindChanged")
    public Object SensitiveFindChanged (@RequestParam("businessModelId")String businessModelId, @RequestParam("businessVersionId")String businessVersionId)
    {
        System.out.println("进入3.6");
        Response response1=new Response();
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(URL_3_6);
        // 设置1个post参数，一个是ds
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("businessModelId", businessModelId));
        parameters.add(new BasicNameValuePair("businessVersionId", businessVersionId));
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(parameters);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("字符串解析异常");
            response1.setCode(406);   //字符串解析异常
            return response1;
        }
        // 将请求实体设置到httpPost对象中
        request.setEntity(formEntity);
        try {
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文
            JSONObject jsonObject = JSONObject.parseObject(result);
            //STATUS  SUCCESS  FAILURE
            String  STATUS = (String) jsonObject.get("STATUS");
            if(STATUS.equals("SUCCESS"))
            {
                response1.setCode(200);
                response1.setObject("success");
                return response1;
            }
            response1.setCode(400);
            response1.setObject("数据源信息不对");
            return response1;
        }catch (Exception e)
        {
            e.printStackTrace();
            response1.setCode(406);
            response1.setObject("字符串解析异常");
            return response1;
        }
    }

    /*
        *  3.7 版本对比  127.0.0.1:7500/sensitive/compareVersion
           参数说明： 表 pf_businessmodel_version
           sourceVersionId 新版本的id   表 pf_businessmodel_version的id
          targetVersionId 旧版本的id  表 pf_businessmodel_version的id
        * */
    @RequestMapping(value = "/SensitiveCompareVersion")
    public Object SensitiveCompareVersion (@RequestParam("sourceVersionId")String sourceVersionId, @RequestParam("targetVersionId")String targetVersionId)
    {
        System.out.println("进入3.7");
        Response response1=new Response();
        HttpClient httpClient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(URL_3_7);
        // 设置1个post参数，一个是ds
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("sourceVersionId", sourceVersionId));
        parameters.add(new BasicNameValuePair("targetVersionId", targetVersionId));
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(parameters);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("字符串解析异常");
            response1.setCode(406);   //字符串解析异常
            return response1;
        }
        // 将请求实体设置到httpPost对象中
        request.setEntity(formEntity);
        try {
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文
            JSONObject jsonObject = JSONObject.parseObject(result);

            //STATUS  SUCCESS  FAILURE
            String  STATUS = (String) jsonObject.get("STATUS");
            if(STATUS.equals("SUCCESS"))
            {
                JSONObject data =jsonObject.getJSONObject("data");
                if(data.containsKey("2"))
                {
                    JSONArray forinstance=data.getJSONArray("2");
                    List<SCReturnSource> scReturnSourceList = JSON.parseObject(forinstance.toJSONString(), new TypeReference<List<SCReturnSource>>() {}); //转化为对象
                    response1.setCode(200);
                    response1.setObject(scReturnSourceList);
                    return response1;
                }
                response1.setCode(202);
                response1.setObject("版本信息相同");
                return response1;
            }
            response1.setCode(400);
            response1.setObject("数据源信息不对");
            return response1;
        }catch (Exception e)
        {
            e.printStackTrace();
            response1.setCode(406);
            response1.setObject("字符串解析异常");
            return response1;
        }
    }

}
