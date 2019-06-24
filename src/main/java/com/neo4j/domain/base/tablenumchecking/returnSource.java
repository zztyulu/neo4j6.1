package com.neo4j.domain.base.tablenumchecking;

import java.util.List;

/**
 * @program: enginegraph
 * @description:  4.1表数一致性校验返回值类型
 *
 * @author: lcy
 * @create: 2019-06-22 16:29
 **/
public class returnSource {
   public List<String> only;
   public List<String> common;
   public int only_num;
   public int common_num;

    public List<String> getOnly() {
        return only;
    }

    public void setOnly(List<String> only) {
        this.only = only;
    }

    public List<String> getCommon() {
        return common;
    }

    public void setCommon(List<String> common) {
        this.common = common;
    }

    public int getOnly_num() {
        return only_num;
    }

    public void setOnly_num(int only_num) {
        this.only_num = only_num;
    }

    public int getCommon_num() {
        return common_num;
    }

    public void setCommon_num(int common_num) {
        this.common_num = common_num;
    }
}
