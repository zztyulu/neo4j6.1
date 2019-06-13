package com.neo4j.test;

/**
 * @program: v1.0
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-06-05 16:14
 **/
public class Data {
    String content;
    String info_from;
    String path;
    String time;
    String title;

    public Data(String content, String info_from, String path, String time, String title) {
        this.content = content;
        this.info_from = info_from;
        this.path = path;
        this.time = time;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInfo_from() {
        return info_from;
    }

    public void setInfo_from(String info_from) {
        this.info_from = info_from;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        String result = "content:" + content + "   info_from:"+ info_from + "   path:" + path + "   time:" + time + "  title:" + title;
        return result;
    }
}
