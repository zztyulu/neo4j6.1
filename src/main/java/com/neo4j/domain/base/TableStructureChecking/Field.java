package com.neo4j.domain.base.TableStructureChecking;

import java.util.List;
/*
* 属性名相同但是类型不同
* */

public class Field {
    public String name;
    public List<String> type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }
}
