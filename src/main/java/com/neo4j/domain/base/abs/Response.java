package com.neo4j.domain.base.abs;


/**
 * @program:  enginegraph
 * @description:  智能搜索返回体
 * @author: liuchenyang
 * @create: 2019-06-14 15.49
 **/


public class Response {
    /*
      状态码code  200 正确 406 字符串解析错误
    */
    private int code;
    private Object object;   //返回实体
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
