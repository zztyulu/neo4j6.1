/**
 * @创建时间: 2019-01-22 14:02
 * @版本: V1.0
 */
package com.neo4j.controller;
import com.neo4j.domain.base.abs.BaseNode;
import com.neo4j.domain.base.abs.Relationship;
import com.neo4j.service.RelationshipService;
import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RelationController {

    Driver driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "root" ));

    @Autowired
    private RelationshipService relationshipService;
    /* @Autowired
     private IPersonService personService;*/
    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public void test(@RequestBody BaseNode baseNode)
    {
        System.out.println(baseNode);
    }

    @RequestMapping(path = "/addRe", method = RequestMethod.POST)
    public void test(@RequestBody Relationship personRelationshipVO) {
      /*  String lable = node.getLable();
        List<Pro> propertys = node.getProperty();
        List<PersonRelationshipVO> relationships = node.getRelationships();*/
        /*  PersonRelationshipVO personRelationshipVO=relationships.get(0);*/

        System.out.println("jinru ");
        String relationcreate = relationshipService.AddRelationship(personRelationshipVO);
        Session session = driver.session();
        String s = "dad";
        //  StatementResult result =session.run(relationcreate);
        //语句出错怎么办？  怎么判断成功
        StatementResult result = session.run(relationcreate);

        session.close();
    }

    @RequestMapping(path = "/deleteRe", method = RequestMethod.POST)
    public void deleteRe(@RequestBody Relationship personRelationshipVO) {

        System.out.println("删除 ");
        String relationcreate = relationshipService.DeleteRelationship(personRelationshipVO);
        Session session = driver.session();
        String s = "dad";
        //  StatementResult result =session.run(relationcreate);
        //语句出错怎么办？  怎么判断成功
        StatementResult result = session.run(relationcreate);

        session.close();
    }

    @RequestMapping(path = "/modifyRe", method = RequestMethod.POST)
    public void modifyRe(@RequestBody Relationship personRelationshipVO) {
        Session session = driver.session();

        System.out.println("修改关系 ");
        //首先删除关系
        String relationcreate = relationshipService.DeleteRelationship(personRelationshipVO);
        session.run(relationcreate);
        //然后添加关系
        relationcreate = relationshipService.AddRelationship(personRelationshipVO);
        session.run(relationcreate);
        String s = "dad";

        //语句出错怎么办？  怎么判断成功
    //    StatementResult result = session.run(relationcreate);

        session.close();
    }

}
