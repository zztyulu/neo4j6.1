package com.neo4j.config;

import org.neo4j.graphdb.RelationshipType;

public enum RelationshipTypes implements RelationshipType {
    IS_FRIEND_OF,
    HAS_SEEN,
    检修设备,
    检修人员,
    检修周期;
}