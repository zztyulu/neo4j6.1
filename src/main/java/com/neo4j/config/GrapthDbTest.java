package com.neo4j.config;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * @program: enginegraph
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-04-28 13:47
 **/


//import org.neo4j.driver.v1.Transaction;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.*;
public class GrapthDbTest {
    static GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File("F:\\neo4j\\neo4j-community-3.5.3\\data\\databases\\graph.db"));

    static{
        System.out.println("Database Load!");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        /*
        添加节点
         */
        Label label = Labels.Person;      //节点类型
        RelationshipType relationshipType = RelationshipTypes.IS_FRIEND_OF;   //关系类型
        Map<String,Object> property = new HashMap<>();                         //属性map
        property.put("name","zzzzzzzt");
        property.put("born","2019");
        Label endLabel = Labels.Person;
        String endNode = "章子怡";                                             //关系的终止节点
        GrapthDbTest.CreateNode(label,property,relationshipType,endLabel,endNode);

        /*
        更新节点
         */
        Map<String,Object> newProperty = new HashMap<>();                         //属性map
        newProperty.put("name","zt");
        newProperty.put("born","2019");
        GrapthDbTest.updateNode(label,property,newProperty);

        /*
        寻找节点
         */
        GrapthDbTest.findNode(label,property);

        /*
        删除节点
         */
        Map<String,Object> property1 = new HashMap<>();                         //属性map
        property1.put("name","Linda");
        //property1.put("born","2019");
        GrapthDbTest.deleteNode(label,property1);

        /*
        创建新增关系
         */
        RelationshipType relationship = RelationshipTypes.IS_FRIEND_OF;
        String start = "Steve";
        String end = "Linda";
        GrapthDbTest.createRelationship(relationship,label,endLabel,start,end);

        /*
        删除关系
         */
        GrapthDbTest.deleteRelationship(relationship,label,start,end);

        /*
        寻找节点关系
         */
        GrapthDbTest.findNodeRelations(label,property);

        //GrapthDbTest.deleteNode(label,property1);      //关系删除后，现在可以删除节点了

    }

    /*
    寻找某节点的所有关系
     */
    public static void findNodeRelations(Label label, Map<String, Object> property) {
        try (Transaction tx = graphDb.beginTx()) {
            // Perform DB operations
            ResourceIterator<Node> iterable = graphDb.findNodes(Labels.Person,property);
            if(iterable == null){
                System.out.println("不存在该目标节点");                   //若执行到此步，应直接退出
            }
            while(iterable.hasNext()){
                Node node = iterable.next();                              //获得该属性的节点
                Iterable<Relationship> allRelations = node.getRelationships();
                Iterator<Relationship> iterator = allRelations.iterator();
                while (iterator.hasNext()) {
                    Relationship relationship = iterator.next();
                    System.out.println(relationship);
                    //System.out.println("寻找到节点：" + node);
                }
            }
            System.out.println("节点相关关系寻找成功" );
            tx.success();
        }
        //查询数据库
        search();
        registerShutdownHook(graphDb);
        System.out.println("Database Shutdown!");
    }

    /*
    添加新增关系
    relationship：关系类型
    label：起始节点的类型
    endLabel：终止节点类型
    start：起始节点名
    end：终止节点名
     */
    public static void createRelationship(RelationshipType relationship, Label startLabel, Label endLabel, String start, String end) {
        try (Transaction tx = graphDb.beginTx()) {
            // Perform DB operations
            ResourceIterator<Node> iterable = graphDb.findNodes(startLabel, "name", start);
            if(iterable == null){
                System.out.println("不存在该起始节点");
            }
            while (iterable.hasNext()) {
                Node current = iterable.next();                                                         // 获取起始节点
                ResourceIterator<Node> endNodes = graphDb.findNodes(endLabel,"name",end);
                if(endNodes == null){
                    System.out.println("不存在该终止节点");
                }
                while(endNodes.hasNext()){
                    Node node = endNodes.next();                                                       //获取终止节点
                    current.createRelationshipTo(node, relationship);
                }
            }
            System.out.println("关系添加成功" );
            tx.success();
        }
        //查询数据库
        search();
        registerShutdownHook(graphDb);
        System.out.println("Database Shutdown!");
    }

    /*
    根据节点的属性标签，找到节点
     */
    public static void findNode(Label label, Map<String, Object> property) {
        try (Transaction tx = graphDb.beginTx()) {
            // Perform DB operations
            ResourceIterator<Node> iterable = graphDb.findNodes(Labels.Person,property);
            if(iterable == null){
                System.out.println("不存在该目标节点");                   //若执行到此步，应直接退出
            }
            while(iterable.hasNext()){
                Node node = iterable.next();                              //获得该属性的节点
                System.out.println("寻找到节点：" + node);
            }
            System.out.println("节点寻找成功" );
            tx.success();
        }
        //查询数据库
        search();
        registerShutdownHook(graphDb);
        System.out.println("Database Shutdown!");
    }

    /*
    更新节点
    label：节点的标签
    property：输入节点的原来相应属性找到该节点
    newProperty：新设置的节点属性
     */
    public static void updateNode(Label label, Map<String, Object> property, Map<String, Object> newProperty) {
        try (Transaction tx = graphDb.beginTx()) {
            // Perform DB operations
            ResourceIterator<Node> iterable = graphDb.findNodes(Labels.Person,property);
            if(iterable == null){
                System.out.println("不存在该目标节点");                   //若执行到此步，应直接退出
            }
            while(iterable.hasNext()){
                Node node = iterable.next();                              //获得该属性的节点
                Iterator iterator = newProperty.keySet().iterator();
                while(iterator.hasNext()){
                    String key = (String)iterator.next();
                    String val =(String) newProperty.get(key);
                    System.out.println("属性名："+key+",值为："+val);
                    node.setProperty(key, val);
                }
            }
            System.out.println("节点更新属性成功" );
            tx.success();
        }
        //查询数据库
        search();
        registerShutdownHook(graphDb);
        System.out.println("Database Shutdown!");
    }

    /*
    创建节点以及节点的关系
    label：节点标签；
    map：属性值对；
    relationType：关系类型；
    endNode：终止节点的名称，后续可根据其他属性确定终止节点，可扩展
     */
    public static void CreateNode(Label lable,Map<String,Object> map,RelationshipType relationshipType,Label endLabel,String endNode){

        try (Transaction tx = graphDb.beginTx()) {
            // Perform DB operations
            Node current = graphDb.createNode(lable);

            //Map<String,Object> map1 = node.getAllProperties();    //输出节点属性
            Iterator iterator = map.keySet().iterator();
            while(iterator.hasNext()){
                String key = (String)iterator.next();
                String val =(String) map.get(key);
                System.out.println("属性名："+key+",值为："+val);
                current.setProperty(key, val);
            }

            ResourceIterator<Node> iterable = graphDb.findNodes(endLabel,"name",endNode);
            if(iterable == null){
                System.out.println("不存在该目标节点");
            }
            while(iterable.hasNext()){
                Node node = iterable.next();
                current.createRelationshipTo(node, relationshipType);
            }

            System.out.println("created node name is" + current.getProperty("name"));
            tx.success();
            tx.close();
        }
        //查询数据库
        search();
        registerShutdownHook(graphDb);
        System.out.println("Database Shutdown!");
    }


    public static void CreateNode(Label lable,Map<String,Object> map){

        try (Transaction tx = graphDb.beginTx()) {
            // Perform DB operations
            Node current = graphDb.createNode(lable);

            //Map<String,Object> map1 = node.getAllProperties();    //输出节点属性
            Iterator iterator = map.keySet().iterator();
            while(iterator.hasNext()){
                String key = (String)iterator.next();
                String val =(String) map.get(key);
                System.out.println("属性名："+key+",值为："+val);
                current.setProperty(key, val);
            }


            //System.out.println("created node name is" + current.getProperty("name"));
            tx.success();
            tx.close();
        }
        //查询数据库
        //search();
        registerShutdownHook(graphDb);
        System.out.println("Database Shutdown!");
    }

    /*
    删除节点
    label:节点标签
    property;节点属性
     */
    public static void deleteNode(Label label, Map<String, Object> property) {

        try (Transaction tx = graphDb.beginTx()) {
            ResourceIterator<Node> iterable = graphDb.findNodes(label,property);   // 获取属性值
            while(iterable.hasNext()){
                Node node = iterable.next();
                node.delete();
                System.out.println("delete节点成功");
            }
            tx.success();
        }catch(ConstraintViolationException e) {
            //System.out.println(e.getMessage());
            System.out.println("该节点还有关系，请先删除关系");
        }
        search();
        registerShutdownHook(graphDb);
        System.out.println("Database Shutdown!");
    }

    public static void deleteRelationship(RelationshipType type, Label label,String start,String end) {

        try (Transaction tx = graphDb.beginTx()) {
            /*
            获取所有关系，遍历每一个关系，比较关系，起始节点是不是一样
             */
            ResourceIterable<Relationship> iter = graphDb.getAllRelationships();
            Iterator<Relationship> iterator = iter.iterator();
            while (iterator.hasNext()) {
                Relationship relationship = iterator.next();
                Node startNode = relationship.getStartNode();
                startNode.getProperties("name");
                Node endNode = relationship.getEndNode();
                if (relationship.isType(type)) {
                    if (start.equals(startNode.getProperty("name")) && end.equals(endNode.getProperty("name"))) {
                        relationship.delete();
                        System.out.println("delete方式1关系成功");
                    }
                }
            }

            /*
            获取起始节点的所有关系，并删除类型和终止节点名一样的关系
             */
            ResourceIterator<Node> iterable = graphDb.findNodes(label, "name", start);   // 获取属性值
            while (iterable.hasNext()) {
                Node node = iterable.next();
                Iterable<Relationship> relationshipIterable = node.getRelationships();
                Iterator<Relationship> relationshipIterator = relationshipIterable.iterator();
                while (relationshipIterator.hasNext()) {
                    Relationship relationship = relationshipIterator.next();
                    if (relationship.isType(type)) {
                        Node endNode = relationship.getEndNode();
                        if (end.equals(endNode.getProperty("name"))) {
                            relationship.delete();
                            System.out.println("delete方式2关系成功");
                        }
                    }
                }
                tx.success();
            }
            search();
            registerShutdownHook(graphDb);
            System.out.println("Database Shutdown!");
        }
    }

    //查询数据库里内容，测试用
    private static void search(){
        String query ="match (n:Person) return n.name as name";
        Map<String, Object >parameters = new HashMap<String, Object>();
        try ( Result result = graphDb.execute( query, parameters ) )
        {
            while ( result.hasNext() )
            {
                Map<String, Object> row = result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s%n", key, row.get( key ) );
                }
            }
        }
    }




    private static void registerShutdownHook(final GraphDatabaseService graphDb){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                graphDb.shutdown();
            }
        });
    }

}