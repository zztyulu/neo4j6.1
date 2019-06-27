package com.neo4j.domain.base.abs.DataAuditTNC;

/**
 * @program: enginegraph
 * @description: 传参
 *   4.1表数一致性校验传参
 * @author: lcy
 * @create: 2019-06-22 16:29
 **/

public class TNCSourceCollection {
    public TNCSource source_num_1;
    public TNCSource source_num_2;

    public TNCSource getSource_num_1() {
        return source_num_1;
    }

    public void setSource_num_1(TNCSource source_num_1) {
        this.source_num_1 = source_num_1;
    }

    public TNCSource getSource_num_2() {
        return source_num_2;
    }

    public void setSource_num_2(TNCSource source_num_2) {
        this.source_num_2 = source_num_2;
    }
}
