package com.neo4j.test;

/**
 * @program: v1.0
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-06-05 15:16
 **/
public class Entity {
    String search;

    public Entity(String search){
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
