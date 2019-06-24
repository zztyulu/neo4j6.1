
package com.neo4j.controller;

import com.neo4j.domain.base.abs.Pro;
import com.neo4j.domain.base.abs.BaseNode;
import com.neo4j.domain.base.abs.Relationship;
import com.neo4j.service.NodeService;
import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class NodeController {


    private  Driver driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "95678" ));

    @Autowired
    private NodeService nodeService;

    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public String test(@RequestBody BaseNode node) {
        String label = node.getLable();
        List<Pro> propertys = node.getProperty();
        List<Relationship> relationships = node.getRelationships();

        String result = nodeService.addNode(label,propertys);
        Session session = driver.session();
        StatementResult statementResult = session.run(result);
        System.out.println(statementResult.toString());
        session.close();
        return "sucess";
        //driver.close();
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody BaseNode node) {

        String label = node.getLable();
        System.out.println(label);
        List<Pro> propertys = node.getProperty();
        List<Relationship> relationships = node.getRelationships();

        String result = nodeService.deleteNode(label,propertys);
        Session session = driver.session();
        session.run(result);
        session.close();
        //driver.close();
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public void update(@RequestBody BaseNode node) {
        String label = node.getLable();
        System.out.println(label);
        List<Pro> propertys = node.getProperty();
        List<Pro> newPropertys = node.getNewPropertys();
        List<Relationship> relationships = node.getRelationships();

        String result = nodeService.updateNode(label,propertys,newPropertys);
        Session session = driver.session();
        session.run(result);
        session.close();
        //driver.close();
    }

    @RequestMapping(path = "/find", method = RequestMethod.POST)
    public void find(@RequestBody BaseNode node) {
//
        String label = node.getLable();
        List<Pro> propertys = node.getProperty();
        List<Pro> newPropertys = node.getNewPropertys();
        List<Relationship> relationships = node.getRelationships();

        String result = nodeService.findNode(label,propertys);
        Session session = driver.session();
        StatementResult st  = session.run(result);
        //        while ( results.hasNext() )
//        {
//            Record record = results.next();
//            System.out.println( record.get( "title" ).asString() + " " + record.get( "name" ).asString() + " " + record.get( "id" ).asString() );
//        }
        session.close();
    }


}
