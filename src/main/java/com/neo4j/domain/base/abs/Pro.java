package com.neo4j.domain.base.abs;

/**
 * @program: enginegraph
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-04-26 20:48
 **/
public class Pro {
    public String Key;
    public String Value;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public String toString() {
        return "Pro{" +
                "Key='" + Key + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }
}
