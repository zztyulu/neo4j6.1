package com.neo4j.domain.base.TableStructureChecking;

import java.util.List;
    /*
            *
            * @program: enginegraph
            * @description:  4.2表结构一致性校验   返回值类型 返回的是List ReturnSource
            * @author: lcy
            * @create: 2019-06-22 21:29

    msg_<i>的数量与传入的待比较的表数有关，表示第i对表的校验结果，在每对表的校验结果中：
     - same_field表示在当前两张表中，约束完全一致的字段名列表；
     - new_in_1表示只在source_num_1中存在的字段名列表；
    - new_in_1表示只在source_num_2中存在的字段名列表；
    - data_type_diff表示两张表中字段名称相同，但数据类型不同的字段名列表；
    - data_length_diff表示两张表中字段名称相同，但数据长度不同的字段名列表。
            */
    public class ReturnSource {

    public List<String> same_field;
    public List<String> new_in_1;
    public List<String> new_in_2;
    public List<Field> data_type_diff;
    public List<DataLD>  data_length_diff;

    public List<String> getSame_field() {
        return same_field;
    }

    public void setSame_field(List<String> same_field) {
        this.same_field = same_field;
    }

    public List<String> getNew_in_1() {
        return new_in_1;
    }

    public void setNew_in_1(List<String> new_in_1) {
        this.new_in_1 = new_in_1;
    }

    public List<String> getNew_in_2() {
        return new_in_2;
    }

    public void setNew_in_2(List<String> new_in_2) {
        this.new_in_2 = new_in_2;
    }

    public List<Field> getData_type_diff() {
        return data_type_diff;
    }

    public void setData_type_diff(List<Field> data_type_diff) {
        this.data_type_diff = data_type_diff;
    }

    public List<DataLD> getData_length_diff() {
        return data_length_diff;
    }

    public void setData_length_diff(List<DataLD> data_length_diff) {
        this.data_length_diff = data_length_diff;
    }
}
