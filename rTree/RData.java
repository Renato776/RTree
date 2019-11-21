package com.rTree;

import java.util.HashMap;

public class RData {
    /*
    * HashTable implementation with pairs <key, Wrapper>
    * I should implement the HashTable myself. However, since we're using java we'll make use of it.
    * If however, you're using C++ you might need to implement it yourself.
    * As for Javascript, I'm not sure but there must be plenty implementations out there XD
    * */
    private HashMap<String, RWrapper> content;

    public RData(){
        content = new HashMap<>();
    }
    public RData getData(String key){
        return content.get(key).data;
    }
    public String getValue(String key){
        return content.get(key).value;
    }
    public RNode getTree(String key){
        return content.get(key).getTree();
    }
    public RWrapper get(String key){
        return content.get(key);
    }
    public boolean contains(String key){
        return content.containsKey(key);
    }
    public void add(String key, RData d){
        content.put(key, new RWrapper(d));
    }
    public void add(String key, String v){
        content.put(key, new RWrapper(v));
    }
    public void add(String key, RNode n){
        content.put(key, new RWrapper(n));
    }
    public void add(String key, RWrapper w){
        content.put(key,w);
    }
    public String encode(){
        String res = "[";
        if(this.content.size()==0){
            //Should never happen, but just in case:
            res += "]";
            return res;
        }
        if(this.content.size()==1){
            res+= content.keySet().toArray()[0]+" : "+((RWrapper)content.values().toArray()[0]).encode();
            res += "]";
            return res;
        }
        res+= content.keySet().toArray()[0]+" : "+((RWrapper)content.values().toArray()[0]).encode()+"\n";
        boolean is_first = true;
        for (var field:this.content.entrySet()) {
            if(is_first){is_first = false; continue;}
            res+="| "+field.getKey()+" : "+field.getValue().encode()+"\n";
        }
        res += "]";
        return res;
    }
}
