package com.neo4j.domain.base.abs.DataAudit;

import com.neo4j.domain.base.abs.DataAudit.Source;

/**
 * @program: enginegraph
 * @description: 传参
 *   4.2 表结构一致性校验
 *   4.3 表记录一致性校验
 * @author: lcy
 * @create: 2019-06-22 16:29
 **/

public class SourceCollection {
    public Source source_num_1;
    public Source source_num_2;

    public Source getSource_num_1() {
        return source_num_1;
    }

    public void setSource_num_1(Source source_num_1) {
        this.source_num_1 = source_num_1;
    }

    public Source getSource_num_2() {
        return source_num_2;
    }

    public void setSource_num_2(Source source_num_2) {
        this.source_num_2 = source_num_2;
    }
}
