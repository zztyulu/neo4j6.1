package com.neo4j.test;

/**
 * @program: v1.0
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-06-05 15:13
 **/
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2019/6/7.
 */
public class RestfulHttpClient {

    private static String REST_API = "http://localhost:5003/search_info/";

    public static void main(String[] args) throws Exception {
        postHttp();
        //getHttp();
    }

    //调用Post方法传参输出
    public static String postHttp() throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        Entity entity = new Entity("南京电网公司南瑞");
        ObjectMapper mapper = new ObjectMapper();

        HttpPost request = new HttpPost(REST_API + "");
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");
        StringEntity requestJson = new StringEntity(mapper.writeValueAsString(entity), "utf-8");
        requestJson.setContentType("application/json");
        request.setEntity(requestJson);
        HttpResponse response = httpClient.execute(request);

        String json = EntityUtils.toString(response.getEntity(),"utf-8");   //获得的为json数据但是里面的中文乱码
        String result = RestfulHttpClient.decodeUnicode(json);                              //转化Json中Unicode为中文

        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray tempdatas = jsonObject.getJSONArray("检索结果");
        System.out.println(tempdatas);
        List<Data> datas = JSON.parseObject(tempdatas.toJSONString(), new TypeReference<List<Data>>() {});          //转化为对象
        for (Data data: datas) {
            //System.out.println(data.toString());
            System.out.println(data.getPath());
        }

        System.out.print("Post result is : " + result + "\n");
        return result;
    }


    //调用get方法传参输出
    public static String getHttp() throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(REST_API + "");
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");
        HttpResponse response = httpClient.execute(request);
        String json = EntityUtils.toString(response.getEntity());
        String result = RestfulHttpClient.decodeUnicode(json);
        System.out.print("Get result is : " + result + "\n");
        return result;
    }


    /*
    Unicode编码转化为中文
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
