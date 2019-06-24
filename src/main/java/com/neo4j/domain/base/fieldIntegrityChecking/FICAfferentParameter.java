package com.neo4j.domain.base.fieldIntegrityChecking;

import com.neo4j.domain.base.abs.DataAudit.Data;

public class FICAfferentParameter {
    public String db_type;
    public String table_name;
    public String field_name;
    public Data data;

    public String getDb_type() {
        return db_type;
    }

    public void setDb_type(String db_type) {
        this.db_type = db_type;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
