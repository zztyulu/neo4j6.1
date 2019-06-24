package com.neo4j.domain.base.fieldIntegrityChecking;

public class FICReturnSource {
    public  Integer table_name_length;
    public  Integer field_content_length;
    public  Integer special_chars;
    public  Integer field_content_empty;

    public Integer getTable_name_length() {
        return table_name_length;
    }

    public void setTable_name_length(Integer table_name_length) {
        this.table_name_length = table_name_length;
    }

    public Integer getField_content_length() {
        return field_content_length;
    }

    public void setField_content_length(Integer field_content_length) {
        this.field_content_length = field_content_length;
    }

    public Integer getSpecial_chars() {
        return special_chars;
    }

    public void setSpecial_chars(Integer special_chars) {
        this.special_chars = special_chars;
    }

    public Integer getField_content_empty() {
        return field_content_empty;
    }

    public void setField_content_empty(Integer field_content_empty) {
        this.field_content_empty = field_content_empty;
    }
}
