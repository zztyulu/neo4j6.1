package com.neo4j.domain.base.DataManagement;


import java.util.List;
/*
* 3.7 返回值类型  list<SCReturnSource>
*
*
* */
public class SCReturnSource {
   public String   id;
    public String   tableName;
    public String   manipulateType;
    public String   status;
    public String   tableComments;
    public List<ColumnManipulate> columnManipulate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getManipulateType() {
        return manipulateType;
    }

    public void setManipulateType(String manipulateType) {
        this.manipulateType = manipulateType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTableComments() {
        return tableComments;
    }

    public void setTableComments(String tableComments) {
        this.tableComments = tableComments;
    }

    public List<ColumnManipulate> getColumnManipulate() {
        return columnManipulate;
    }

    public void setColumnManipulate(List<ColumnManipulate> columnManipulate) {
        this.columnManipulate = columnManipulate;
    }
}
