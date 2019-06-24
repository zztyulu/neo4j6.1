package com.neo4j.domain.base.tablenumchecking;

/**
 * @program: enginegraph
 * @description:  4.1表数一致性校验返回值类型
 *
 * @author: lcy
 * @create: 2019-06-22 21:29
 **/

public class Msg {
    public returnSource source_num_1;
    public  returnSource source_num_2;

    public returnSource getSource_num_1() {
        return source_num_1;
    }

    public void setSource_num_1(returnSource source_num_1) {
        this.source_num_1 = source_num_1;
    }

    public returnSource getSource_num_2() {
        return source_num_2;
    }

    public void setSource_num_2(returnSource source_num_2) {
        this.source_num_2 = source_num_2;
    }
}
