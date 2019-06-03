package com.neo4j.service.impl;

import com.neo4j.domain.base.abs.BaseNode;
import com.neo4j.domain.base.abs.Pro;
import com.neo4j.domain.base.abs.Relationship;
import com.neo4j.service.RelationshipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServiceImpl implements RelationshipService {
    @Override
    /*
     *
     * */
    public String AddRelationship(Relationship relationship) {
        //提取属性
        StringBuilder stringBuilder=new StringBuilder();
        BaseNode startNode=relationship.getStartNode();
        List<Pro> startNodePro=startNode.getPropertys();
        BaseNode endNode=relationship.getEndNode();
        List<Pro> endNodePro=endNode.getPropertys();
        String relationshiplabel=relationship.getLabel();
        List<Pro> relationshipPro=relationship.getMypro();
/*
postman  传递的json
{"label": "aaa","mypro": [{"Key":"re","Value":"re"},{"Key":"Value","Value":"22"}],
"startNode":{"lable":"onePerson","propertys":[{"Key":"Key","Value":"11"},{"Key":"Value","Value":"22"}]},
"endNode":{"lable":"onePerson","propertys":[{"Key":"Key1","Value":"22"},{"Key":"Value1","Value":"33"}]}
}
对应的cql语句
    match(a:Person {name:'a', born:1997}),(b:Person {name:'a', born:1997})
    CREATE (a)-[r:DO_SHOPPING_WITH{shopdate:"12/12/2014",price:55000}]->(b)
    属性和标签不加""  但是属性值必须为""

* */

        //起始节点
        stringBuilder.append("match (a:");
        stringBuilder.append(startNode.getLable()+"{");
        for(int i=0;i<startNodePro.size()-1;i++)
            stringBuilder.append(startNodePro.get(i).Key+":\""+startNodePro.get(i).Value+"\",");
        stringBuilder.append(startNodePro.get(startNodePro.size()-1).Key+":\""+startNodePro.get(startNodePro.size()-1).Value+"\"}),");
        //终止节点
        stringBuilder.append("(b:");
        stringBuilder.append(endNode.getLable()+"{");
        for(int i=0;i<endNodePro.size()-1;i++)
            stringBuilder.append(endNodePro.get(i).Key+":\""+endNodePro.get(i).Value+"\",");
        stringBuilder.append(endNodePro.get(endNodePro.size()-1).Key+":\""+endNodePro.get(endNodePro.size()-1).Value+"\"}) ");
        //关系
        stringBuilder.append(" CREATE (a)-[r:");
        stringBuilder.append(relationshiplabel+"{");
        for(int i=0;i<relationshipPro.size()-1;i++)
            stringBuilder.append(relationshipPro.get(i).Key+":\""+relationshipPro.get(i).Value+"\",");
        stringBuilder.append(relationshipPro.get(relationshipPro.size()-1).Key+":\""+relationshipPro.get(endNodePro.size()-1).Value+"\"}]->(b) ");
        return stringBuilder.toString();
    }

    /*
    postman  传递的json
    {"label": "aaa","mypro": [{"Key":"re","Value":"re"},{"Key":"Value","Value":"22"}],
    "startNode":{"lable":"onePerson","propertys":[{"Key":"Key","Value":"11"},{"Key":"Value","Value":"22"}]},
    "endNode":{"lable":"onePerson","propertys":[{"Key":"Key1","Value":"22"},{"Key":"Value1","Value":"33"}]}
    }
    对应的cql语句
        match(a:Person {name:'a', born:1997})-[rel:label]-(b:Person {name:'a', born:1997}) delete rel

     match(a:Person {name:'a', born:1997}),(b:Person {name:'a', born:1997})
    CREATE (a)-[r:DO_SHOPPING_WITH{shopdate:"12/12/2014",price:55000}]->(b)

        属性和标签不加""  但是属性值必须为""

    * */
    @Override
    public String DeleteRelationship(Relationship relationship) {
        //提取属性
        StringBuilder stringBuilder=new StringBuilder();
        BaseNode startNode=relationship.getStartNode();
        List<Pro> startNodePro=startNode.getPropertys();
        BaseNode endNode=relationship.getEndNode();
        List<Pro> endNodePro=endNode.getPropertys();
        String relationshiplabel=relationship.getLabel();
        List<Pro> relationshipPro=relationship.getMypro();

        //起始节点
        stringBuilder.append("match (a:");
        stringBuilder.append(startNode.getLable()+"{");
        for(int i=0;i<startNodePro.size()-1;i++)
            stringBuilder.append(startNodePro.get(i).Key+":\""+startNodePro.get(i).Value+"\",");
        stringBuilder.append(startNodePro.get(startNodePro.size()-1).Key+":\""+startNodePro.get(startNodePro.size()-1).Value+"\"})");

        //关系
        stringBuilder.append("-[rel:"+relationshiplabel+"]-");
        //终止节点
        stringBuilder.append("(b:");
        stringBuilder.append(endNode.getLable()+"{");
        for(int i=0;i<endNodePro.size()-1;i++)
            stringBuilder.append(endNodePro.get(i).Key+":\""+endNodePro.get(i).Value+"\",");
        stringBuilder.append(endNodePro.get(endNodePro.size()-1).Key+":\""+endNodePro.get(endNodePro.size()-1).Value+"\"}) ");

        stringBuilder.append("delete rel");
        return stringBuilder.toString();
    }
}
