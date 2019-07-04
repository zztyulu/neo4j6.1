package com.neo4j.repository;

import com.neo4j.entity.Assesscountmxb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * 供应商评价持久层
 * @author njustz
 * @date 2019/7/3
 */

public interface AssesscountmxbRepository extends JpaRepository<Assesscountmxb, Integer> {

    Page<Assesscountmxb> findByVendorId(String vendorId, Pageable pageable);
}
