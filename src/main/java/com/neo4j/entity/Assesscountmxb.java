package com.neo4j.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author njustz
 * @date 2019/7/3
 */
@Entity
@Table(name = "assesscountmxb")
@Data
public class Assesscountmxb {

    @Id
    private Integer assesscountId;

    private String vendorId;

    private String vendorName;

    private Integer processScorecl;

    private Integer deliveryScorecl;

    private Integer arrivalScoredsb;

    private Integer fieldScorecl;

    private Integer qualityScorecl;

    private Integer customerScorecl;

    private Integer score;

}
