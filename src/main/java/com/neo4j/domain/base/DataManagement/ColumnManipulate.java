package com.neo4j.domain.base.DataManagement;
/*
* 3.7 columnManipulate 表相关的字段字典
 *
* */

public class ColumnManipulate {
    public String columnComments;
    public String columnLength;
    public String columnName;
    public String columnType;
    public String id;
    public String manipulateType;
    public String oldColumnLength;
    public String oldColumnType;
    public String oldColumnComments;
    public String tableManipulateId;

    public String getColumnComments() {
        return columnComments;
    }

    public void setColumnComments(String columnComments) {
        this.columnComments = columnComments;
    }

    public String getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(String columnLength) {
        this.columnLength = columnLength;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManipulateType() {
        return manipulateType;
    }

    public void setManipulateType(String manipulateType) {
        this.manipulateType = manipulateType;
    }

    public String getOldColumnLength() {
        return oldColumnLength;
    }

    public void setOldColumnLength(String oldColumnLength) {
        this.oldColumnLength = oldColumnLength;
    }

    public String getOldColumnType() {
        return oldColumnType;
    }

    public void setOldColumnType(String oldColumnType) {
        this.oldColumnType = oldColumnType;
    }

    public String getOldColumnComments() {
        return oldColumnComments;
    }

    public void setOldColumnComments(String oldColumnComments) {
        this.oldColumnComments = oldColumnComments;
    }

    public String getTableManipulateId() {
        return tableManipulateId;
    }

    public void setTableManipulateId(String tableManipulateId) {
        this.tableManipulateId = tableManipulateId;
    }
}
