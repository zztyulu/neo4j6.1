package com.neo4j.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo4j.domain.base.abs.Response;
import com.neo4j.test.Data;
import com.neo4j.test.Entity;
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

import java.util.List;

/**
 * @program:  enginegraph
 * @description:  智能搜索
 * @author: liuchenyang
 * @create: 2019-06-14 15.49
 **/




@RestController
public class AisearchController {

    private  String REST_API = "http://localhost:5003/search_info/";

    @RequestMapping("/Aiserach")
    public Object search (@RequestBody String search)
    {
        System.out.println("进入");
        Response response1=new Response();

        HttpClient httpClient = new DefaultHttpClient();
        //Entity entity = new Entity("南京电网公司南瑞");
        ObjectMapper mapper = new ObjectMapper();

        HttpPost request = new HttpPost(REST_API + "");
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");
    //   StringEntity requestJson = new StringEntity(mapper.writeValueAsString(entity), "utf-8");
        StringEntity requestJson = new StringEntity(search, "utf-8");
        requestJson.setContentType("application/json");
        request.setEntity(requestJson);
        try {
            HttpResponse response = httpClient.execute(request);

            String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
            String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文

            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray tempdatas = jsonObject.getJSONArray("检索结果");
         //   System.out.println(tempdatas);
            List<Data> datas = JSON.parseObject(tempdatas.toJSONString(), new TypeReference<List<Data>>() {});          //转化为对象

      /*  for (Data data: datas) {
            //System.out.println(data.toString());
            System.out.println(data.getPath());
        }*/
            // System.out.print("Post result is : " + result + "\n");
            response1.setCode(200);
            response1.setObject(datas);
            return response1;
        }catch (Exception e)
        {
            response1.setCode(400);
            return response1;
        }
    }




}
