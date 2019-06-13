package com.neo4j.service.impl;

import com.neo4j.test.Labels;
import com.neo4j.domain.base.abs.Pro;
import com.neo4j.service.NodeService;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @program: enginegraph
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-05-13 16:09
 **/
@Service
public class NodeServiceImpl implements NodeService {
    GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File("F:\\neo4j\\neo4j-community-3.5.3\\data\\databases\\graph.db"));

    @Override
    //CREATE (a:Person {name:‘a’, born:1997}) return a;
    public String addNode(String label, List<Pro> propertys) {
        StringBuilder str = new StringBuilder("CREATE (a:");
        str.append(label+" {");
        for (int i = 0; i < propertys.size(); i++) {
            Pro myPro =  propertys.get(i);
            String key = myPro.getKey();
            String value = myPro.getValue();
            str.append(key + ":");
            str.append("'" + value + "'");
            if(i<propertys.size()-1){
                str.append(",");
            }
        }
        str.append("})");
        String result = str.toString();
        return result;
    }

    @Override
    //MATCH(n:Person{name:‘d’})delete n;
    public String deleteNode(String label, List<Pro> propertys) {
        StringBuilder str = new StringBuilder("MATCH (a:");
        str.append(label+" {");
        for (int i = 0; i < propertys.size(); i++) {
            Pro pro =  propertys.get(i);
            String key = pro.getKey();
            String value = pro.getValue();
            str.append(key + ":");
            str.append("'" + value + "'");
            if(i<propertys.size()-1){
                str.append(",");
            }
        }
        str.append("}) delete a;");
        String result = str.toString();
        return result;
    }

    @Override
    //MATCH (n:Person)WHERE n.name="a"SET n.born = 2003 RETURN n;
    public String updateNode(String label, List<Pro> propertys, List<Pro> newPropertys) {
        StringBuilder str = new StringBuilder("MATCH (a:");
        str.append(label+")");
        if(propertys.size()!=0){            //存在属性,找到相应节点，此处可以为0
            str.append("where ");
            for (int i = 0; i < propertys.size(); i++) {
                Pro pro =  propertys.get(i);
                String key = pro.getKey();
                String value = pro.getValue();
                str.append("a."+ key + "=");
                str.append("'"+ value + "'");
                if(i>=0 && i<propertys.size()-1){
                    str.append("AND ");
                }
            }
        }
        str.append(" SET ");
        if(newPropertys.size()!=0){        //修改属性
            for (int i = 0; i < propertys.size(); i++) {
                Pro pro =  newPropertys.get(i);
                String key = pro.getKey();
                String value = pro.getValue();
                str.append("a."+ key + "=");
                str.append("'"+ value + "'");
                if(i>=0 && i<propertys.size()-1){
                    str.append(",");                                //注意这里是，而不是AND
                }
            }
        }
        String res = str.toString();
        return res;
    }

    @Override
    public  Map<String,Object> findNode(Label label, Map<String, Object> property) {
        Map<String,Object> properties = new HashMap<>();
        Map<String,Object> rela = new HashMap<>();
        java.lang.Iterable<Relationship> relationships;
        try (Transaction tx = graphDb.beginTx()) {
            ResourceIterator<Node> iterable = graphDb.findNodes(Labels.Person,property);
            if(iterable == null){
                System.out.println("不存在该目标节点");                   //若执行到此步，应直接退出
            }
            while(iterable.hasNext()){
                Node node = iterable.next();                              //获得该属性的节点
                properties = node.getAllProperties();
                System.out.println("寻找到该节点，编号为：" + node);
                System.out.println("其包含属性如下：");
                Iterator iterator0 = properties.keySet().iterator();
                while(iterator0.hasNext()){
                    String key = (String)iterator0.next();
                    String val =(String) properties.get(key);
                    System.out.println("属性名："+key+",值为："+val);
                }
                System.out.println("****************");
               relationships =  node.getRelationships();
               Iterator<Relationship> iterator = relationships.iterator();
               while(iterator.hasNext()){
                   Relationship relationship = iterator.next();
                   RelationshipType type = relationship.getType();
                   Node endNode = relationship.getEndNode();
                   System.out.println("查找到节点"+endNode+"和该节点有关系，关系为："+type);
                   //properties.putAll(relationship.getAllProperties());
               }

            }
            System.out.println("节点寻找成功" );
            tx.success();
        }
        registerShutdownHook(graphDb);
        System.out.println("Database Shutdown!");
        return properties;
    }

    private static void registerShutdownHook(final GraphDatabaseService graphDb){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                graphDb.shutdown();
            }
        });
    }

    @Override
    public String findNode(String label, List<Pro> propertys) {
        StringBuilder str = new StringBuilder("MATCH (a:");
        str.append(label+" {");
        for (int i = 0; i < propertys.size(); i++) {
            Pro pro =  propertys.get(i);
            String key = pro.getKey();
            String value = pro.getValue();
            str.append(key + ":");
            str.append("'" + value + "'");
            if(i<propertys.size()-1){
                str.append(",");
            }
        }
        str.append("}) return a;");
        String result = str.toString();
        return result;
    }
}
