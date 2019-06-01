package com.neo4j.controller;
import com.neo4j.config.Labels;
import com.neo4j.domain.base.abs.Pro;
import com.neo4j.domain.base.abs.BaseNode;
import com.neo4j.domain.base.abs.Relationship;
import com.neo4j.service.NodeService;
import org.neo4j.graphdb.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @program: enginegraph
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-05-24 11:58
 **/
@RestController
@RequestMapping("/search")
public class findController {

    @Autowired
    private NodeService nodeService;


    @RequestMapping(path = "/find", method = RequestMethod.POST)
    public Map<String, Object> find(@RequestBody BaseNode node) {

        String strlabel = node.getLable();
        Label label = Labels.MOVIE;
        if (strlabel == "Person") {
            label = Labels.Person;
        } else {
            if (strlabel == "User") {
                label = Labels.USER;
            } else {
                System.out.println("不存在该Label");
            }
        }
        List<Pro> propertys = node.getProperty();
        //List<Pro> newPropertys = node.getNewPropertys();
        List<Relationship> relationships = node.getRelationships();
        Map<String, Object> mapProperty = new HashMap<>();

        for (int i = 0; i < propertys.size(); i++) {
            Pro pro = propertys.get(i);
            String name = pro.getKey();
            String value = pro.getValue();
            mapProperty.put(name, value);
        }
        Map<String, Object> result = nodeService.findNode(label, mapProperty);
        return result;
    }

}
