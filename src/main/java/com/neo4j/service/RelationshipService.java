package com.neo4j.service;


import com.neo4j.domain.base.abs.Relationship;

public interface RelationshipService {
    //根据已经有的节点创建关系
    public String AddRelationship(Relationship relationship);
}
