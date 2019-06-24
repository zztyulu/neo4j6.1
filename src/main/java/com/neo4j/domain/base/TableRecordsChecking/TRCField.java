package com.neo4j.domain.base.TableRecordsChecking;

import java.util.List;

public class TRCField {
    public String name;
    public List<Object> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
