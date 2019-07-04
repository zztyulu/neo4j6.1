package com.neo4j.controller;

import com.neo4j.entity.Assesscountmxb;
import com.neo4j.repository.AssesscountmxbRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 供应商评价controller
 * @author njustz
 * @date 2019/7/4
 */

@RestController
@RequestMapping("/vendor")
@Slf4j
public class VendorEvalController {

    @Autowired
    private AssesscountmxbRepository repository;

    //评价详细列表
    @GetMapping("/list")
    public List<Assesscountmxb> list(){

        List<Assesscountmxb> assesscountmxbList = repository.findAll();

        return  assesscountmxbList;
    }

}
