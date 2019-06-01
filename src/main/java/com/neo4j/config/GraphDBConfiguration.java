/**
 * @Copyright (C) 2019 广州金鹏集团有限公司.
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 * @创建人: 赵力
 * @创建时间: 2019-01-22 11:56
 * @版本: V1.0
 */
package com.neo4j.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
* 嵌入式端口
*
*
*
* */


@Configuration
@EnableNeo4jRepositories(basePackages = "com.ikcai.enginegraph.respository")
@EntityScan(basePackages = "com.ikcai.enginegraph.domain")
@EnableTransactionManagement
public class GraphDBConfiguration{
}
