package com.neo4j.service;

import com.neo4j.domain.base.abs.Pro;
import org.neo4j.graphdb.Label;

import java.util.List;
import java.util.Map;

public interface NodeService {
    String addNode(String label, List<Pro> propertys);

    String deleteNode(String label, List<Pro> propertys);

    String updateNode(String label, List<Pro> propertys, List<Pro> newPropertys);

    Map<String,Object> findNode(Label label, Map<String, Object> property);

    String findNode(String label, List<Pro> propertys);
}
