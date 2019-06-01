package com.neo4j.domain.base.abs;


import org.neo4j.ogm.annotation.*;

import java.util.List;

/**
 * @program: enginegraph
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-04-26 16:29
 **/
@NodeEntity
public class BaseNode {
    /**
     * id 必须为Long类型，而且必须提供(节点和关系都需要)。且要加这个注解。
     * id 由图数据库统一操作，所以不需要setter
     */
    @Id
    @GeneratedValue
    private Long id;

    @Labels
    private String lable;
    private List<Pro> propertys;                //属性列表
    private List<Pro> newPropertys;
    private List<Relationship> relationships; //关系列表

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }


//    @Property(name = "name")
//    private String name; //名称

    public void setId(Long id) {
        this.id = id;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public List<Pro> getProperty() {
        return propertys;
    }

    public void setProperty(List<Pro> property) {
        this.propertys = property;
    }

    public List<Pro> getPropertys() {
        return propertys;
    }

    public void setPropertys(List<Pro> propertys) {
        this.propertys = propertys;
    }

    public List<Pro> getNewPropertys() {
        return newPropertys;
    }

    public void setNewPropertys(List<Pro> newPropertys) {
        this.newPropertys = newPropertys;
    }






    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        if (this.id == null) {
            // For newly created entity, id will be null
            return false;
        }

        BaseNode entity = (BaseNode) obj;
        return this.id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }

    @Override
    public String toString() {
        return "BaseNode{" +
                "id=" + id +
                ", lable='" + lable + '\'' +
                ", propertys=" + propertys +
                ", newPropertys=" + newPropertys +
                ", relationships=" + relationships +
                '}';
    }
}
