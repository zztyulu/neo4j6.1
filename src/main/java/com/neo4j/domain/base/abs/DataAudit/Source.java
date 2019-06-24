package com.neo4j.domain.base.abs.DataAudit;

import java.util.List;

public class Source {
    String db_type;
    Data data;
    List<String> table;
    public String getDb_type() {
        return db_type;
    }
    public void setDb_type(String db_type) {
        this.db_type = db_type;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<String> getTable() {
        return table;
    }

    public void setTable(List<String> table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Source{" +
                "db_type='" + db_type + '\'' +
                ", data=" + data +
                ", table=" + table +
                '}';
    }
}
