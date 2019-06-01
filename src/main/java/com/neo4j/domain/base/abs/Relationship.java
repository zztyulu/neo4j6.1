package com.neo4j.domain.base.abs;

import java.util.List;

public class Relationship {
    private String label; //关系名字
    private List<Pro> mypro; //关系属性
    private BaseNode startNode;
    private BaseNode endNode;

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public List<Pro> getMypro() {
        return mypro;
    }

    public void setMypro(List<Pro> mypro) {
        this.mypro = mypro;
    }

    public BaseNode getStartNode() {
        return startNode;
    }
    public void setStartNode(BaseNode startNode) {
        this.startNode = startNode;
    }
    public BaseNode getEndNode() {
        return endNode;
    }
    public void setEndNode(BaseNode endNode) {
        this.endNode = endNode;
    }

    @Override
    public String toString() {
        return "Relationship{" +
                "label='" + label + '\'' +
                ", mypro=" + mypro +
                ", startNode=" + startNode +
                ", endNode=" + endNode +
                '}';
    }
}
