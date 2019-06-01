package com.neo4j.config;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: enginegraph
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-05-05 16:03
 **/
public class SingleTest {
    public static void main(String[] args) {
        GrapthDbTest test = new GrapthDbTest();

        Label label = Labels.Department;      //节点类型
        Label label1 = Labels.人员;
        Label label2 = Labels.周期;
        Label label3= Labels.设备;
        RelationshipType relationshipType = RelationshipTypes.IS_FRIEND_OF;   //关系类型
        Map<String,Object> property1 = new HashMap<>();                         //属性map
        property1.put("name","zzt");
        //property1.put("born","2019");
        Map<String,Object> property2= new HashMap<>();                         //属性map
        property2.put("name","lcy");
        Map<String,Object> property3= new HashMap<>();                         //属性map
        property3.put("name","周1944");
        Map<String,Object> property4= new HashMap<>();                         //属性map
        property4.put("name","章子怡");
        property4.put("性别","女");
        property4.put("工作","演员");
        property4.put("老公","汪峰");
        property4.put("家庭","未知");
        Map<String,Object> property5= new HashMap<>();
        property5.put("name","设备检修项目");//属性map
        property5.put("jxxmbm","010304");
        property5.put("jxxm","A.1整体更换");
        property5.put("obj_id","9CFAFB6B7-FEDR");
        property5.put("odsdatauptime","20190515");
        Map<String,Object> property6= new HashMap<>();
        property6.put("name","设备检修项目");//属性map
        property6.put("jxxmbm","010304");
        property6.put("jxxm","C.2设备清扫");
        property6.put("obj_id","9CFAFB6B7-FEDR");
        property6.put("odsdatauptime","20190525");
//        test.CreateNode(label,property1,relationshipType,label,"zzt");
//        test.CreateNode(label,property2,relationshipType,label,"zzt");
//        test.CreateNode(label,property2,relationshipType,label,"zzt");
//        test.CreateNode(label,property4,relationshipType,label,"zzt");
        //test.CreateNode(label,property5);
        test.CreateNode(label,property6);

        RelationshipType relationshipType1 = RelationshipTypes.检修设备;
        RelationshipType relationshipType2 = RelationshipTypes.检修周期;
        RelationshipType relationshipType3 = RelationshipTypes.检修人员;
        Map<String,Object> property7= new HashMap<>();
        property7.put("name","设备");//属性map
        Map<String,Object> property8= new HashMap<>();
        property8.put("name","周期");//属性map
        Map<String,Object> property9= new HashMap<>();
        property9.put("name","人员");//属性map
        //test.CreateNode(label3,property7,relationshipType1,label,"设备检修项目");
        test.CreateNode(label2,property8,relationshipType2,label,"设备检修项目");
        test.CreateNode(label1,property9,relationshipType3,label,"设备检修项目");

        //test.updateNode(label,property1,property2);
        //test.deleteRelationship(relationshipType,label,"woshininini","章子怡");
        //test.deleteNode(label,property2);
        //test.deleteNode(label,property3);
        //test.createRelationship(relationshipType,label,label,"周子韬","章子怡");


        //Label label = Labels.Person;      //节点类型
        //RelationshipType relationshipType = RelationshipTypes.IS_FRIEND_OF;   //关系类型
        Map<String,Object> property = new HashMap<>();                         //属性map
        property.put("name","周周");
        property.put("born","2019");
        Label endLabel = Labels.Person;
        String endNode = "章子怡";                                             //关系的终止节点
        test.CreateNode(label,property,relationshipType,endLabel,endNode);


    }
}
