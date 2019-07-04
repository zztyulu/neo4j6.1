package com.neo4j.repository;

import com.neo4j.entity.Assesscountmxb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssesscountmxbRepositoryTest {

    @Autowired
    private AssesscountmxbRepository assesscountmxbRepository;

    private final String VENDERID = "26923";

    @Test
    public void saveTest(){
        Assesscountmxb assesscountmxb = new Assesscountmxb();
        assesscountmxb.setAssesscountId(11);
        assesscountmxb.setVendorId("1111");
        Assesscountmxb result = assesscountmxbRepository.save(assesscountmxb);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByVendorIdTest(){
        PageRequest request = PageRequest.of(0, 1);
        Page<Assesscountmxb> result = assesscountmxbRepository.findByVendorId(VENDERID, request);
        Assert.assertNotEquals(0, result.getTotalElements());
        System.out.println(result.getTotalElements());
    }

}