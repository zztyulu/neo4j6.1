package com.neo4j.domain.base.abs.DataAuditTNC;

import com.neo4j.domain.base.abs.DataAudit.Data;

import java.util.List;

public class TNCSource {
    String db_type;
    Data data;
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
    @Override
    public String toString() {
        return "Source{" +
                "db_type='" + db_type + '\'' +
                ", data=" + data +
                '}';
    }
}
