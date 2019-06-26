package com.neo4j.controller;

import com.neo4j.util.Neo4jUtil;
import com.neo4j.util.R;
import com.neo4j.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/neo4j")
public class KGManageController {
    @Autowired
    private Neo4jUtil neo4jUtil;

    @GetMapping("/")
    public String home(Model model) {
        return "kg/home";
    }



    @ResponseBody
    @RequestMapping(value = "/getcypherresult")
    public R<HashMap<String, Object>> getcypherresult(String cypher) {
        R<HashMap<String, Object>> result = new R<HashMap<String, Object>>();
        String error="";
        try {
            HashMap<String, Object> graphData = neo4jUtil.GetGraphNodeAndShip(cypher);
            result.code = 200;
            result.data = graphData;
        } catch (Exception e) {
            e.printStackTrace();
            result.code = 500;
            error=e.getMessage();
            result.setMsg("服务器错误");
        }
        finally {
            if(StringUtil.isNotBlank(error)){
                result.code = 500;
                result.setMsg(error);
            }
        }
        return result;
    }
}
