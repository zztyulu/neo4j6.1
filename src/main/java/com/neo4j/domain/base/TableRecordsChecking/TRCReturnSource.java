package com.neo4j.domain.base.TableRecordsChecking;

/*
*返回的值类型是 list<TRCReturnSource>
*
*
* msg_<i>的数量与传入的待比较的表数有关，表示第i对表的校验结果，在每对表的校验结果中：
- diff_field表示在当前两张表中，内容不同的字段名称列表；
- in_table1表示在第一个数据源的对应表中字段单独含有的值，为python字典形式，键为字段名称，值为单独含有的值的列表；
- in_table2表示在第二个数据源的对应表中字段单独含有的值，为python字典形式，键为字段名称，值为单独含有的值的列表；
- same_field表示两个数据源的对应表中的数据完全相同的字段名列表。
*
* */

import java.util.List;

public class TRCReturnSource {
    public List<String> same_field;
    public List<String> diff_field;
    public List<TRCField> in_table1;
    public List<TRCField> in_table2;

    public List<String> getSame_field() {
        return same_field;
    }

    public void setSame_field(List<String> same_field) {
        this.same_field = same_field;
    }

    public List<String> getDiff_field() {
        return diff_field;
    }

    public void setDiff_field(List<String> diff_field) {
        this.diff_field = diff_field;
    }

    public List<TRCField> getIn_table1() {
        return in_table1;
    }

    public void setIn_table1(List<TRCField> in_table1) {
        this.in_table1 = in_table1;
    }

    public List<TRCField> getIn_table2() {
        return in_table2;
    }

    public void setIn_table2(List<TRCField> in_table2) {
        this.in_table2 = in_table2;
    }
}
