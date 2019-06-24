package com.neo4j.domain.base.TableStructureChecking;

import java.util.List;

/*
*
* 属性名相同但是长度不同
* */

public class DataLD {
    public String name;
    public List<Integer> integer;   //指类型的长度

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Integer> getInteger() {
        return integer;
    }
    public void setInteger(List<Integer> integer) {
        this.integer = integer;
    }
}
