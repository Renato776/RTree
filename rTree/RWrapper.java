package com.rTree;

public class RWrapper {
    RData data;
    RNode tree;
    String value;
    public RWrapper(RData d){
        data = d;
        tree = null;
        value = null;
    }
    public RWrapper(RNode n){
        tree = n;
        data = null;
        value = null;
    }
    public RWrapper(String v){
        value = v;
        data =  null;
        tree = null;
    }
    public RNode getTree(){
        if(tree!=null)return tree;
        System.out.println("Fatal error! Requested: Tree. Tree not found.");
        System.out.println("Instead found: value: "+value+" data:"+data);
        return null;
    }
    public RData getData(){
        if(data!=null)return data;
        System.out.println("Fatal error! Requested: Data. Data not found.");
        System.out.println("Instead found: value: "+value+" tree:"+tree);
        return null;
    }
    public String getValue(){
        if(value!=null)return value;
        System.out.println("Fatal error! Requested: Value. Value not found.");
        System.out.println("Instead found: data: "+data+" tree:"+tree);
        return "";
    }
    public String encode(){
        if(data!=null)return data.encode();
        if(tree!=null)return tree.encode();
        return "<"+value+">";
    }
}
