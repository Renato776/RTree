package com.rTree;

import java.util.LinkedList;

public class RNode {
    public LinkedList<RNode> children;
    public RData data;
    public RNode(){
        children = new LinkedList<>();
    }
    public RNode(RData d){
        data = d;
        children = new LinkedList<>();
    }
    public RNode(LinkedList<RNode> c){
        data = null;
        children = c;
    }
    public RNode(RData d, LinkedList<RNode> c){
        data = d;
        children = c;
    }
    public void add(RNode n){
        children.add(n);
    }
    public RData getData(String key){
        return data.getData(key);
    }
    public RNode getTree(String key){
        return data.getTree(key);
    }
    public String getValue(String key){
        return data.getValue(key);
    }
    public RData getAllContent(){
        return data;
    }
    public boolean is_leaf(){
        return children.isEmpty();
    }
    public String encode(){
        String res = "{";
        if(data!=null){
            res+= data.encode()+"\n";
        }
        for (RNode n : children) {
            res+= n.encode();
        }
        res+= "}";
        return res;
    }
}
