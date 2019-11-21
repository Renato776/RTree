package com.rTree;

import java.util.LinkedList;
import java.util.Stack;

public class RUniverse {
    /*
    * Helper class for building the actual structure usable tree from the parse tree.
    * */
    GNode root;
    Stack<String> stack;
    Stack<Object> evaluation_stack;
    public RUniverse(GNode node){
        root = node;
        stack = new Stack<>();
        evaluation_stack = new Stack<>();
    }
    public void evaluate_node(GNode node){
        if(node.token){
            evaluation_stack.push(node.text);
            return;
        }
        switch (node.name){
            case "node":
                for (GNode n:node.children) {
                    evaluate_node(n);
                }
                RData data = null;
                LinkedList<RNode> childs = null;
                try {
                    childs = (LinkedList<RNode>)evaluation_stack.pop();
                    data = (RData)evaluation_stack.pop();
                    if(childs == null)throw new Exception();
                }catch (Exception ex){
                    System.out.println("An error occurred while compiling children Nodes.");
                    System.out.println(node.children.getLast().get_error_backtrace());
                }
                if(data == null){
                    //No problem, no data was provided.
                    evaluation_stack.push(new RNode(childs));
                    return;
                }
                evaluation_stack.push(new RNode(data,childs));
                return;
            case "children":
                LinkedList<RNode> children = new LinkedList<>();
                for (GNode n: node.children) {
                    try{
                        evaluate_node(n);
                        children.add((RNode) evaluation_stack.pop());
                    }catch (Exception ex){
                        System.out.println("An error has occurred while compiling child node");
                        System.out.println(n.get_error_backtrace());
                        return;
                    }
                }
                evaluation_stack.push(children);
                return;
            case "data":
                if(node.children.isEmpty()){
                    evaluation_stack.push(null);
                    return;
                }
                evaluate_node(node.children.getFirst());
                return;
            case "fields":
                //Alright, data compilation happens here.
                RData res = new RData();
                for (GNode n :node.children) {
                    try{
                        evaluation_stack.push(res);
                        evaluate_node(n);
                        RWrapper w = (RWrapper) evaluation_stack.pop();
                        String key = (String) evaluation_stack.pop();
                        res.add(key,w);
                    }catch (Exception ex){
                        System.out.println("An error occurred while compiling data.");
                        System.out.println(n.get_error_backtrace());
                    }
                }
                evaluation_stack.push(res);
                return;
            case "field":
                RData parent = (RData)evaluation_stack.pop();
                String name  = node.children.getFirst().text;
                if(parent.contains(name)){
                    System.out.println("Semantic error.");
                    System.out.println("Field names must be unique.");
                    System.out.println("Repeated name: "+name);
                    System.out.println(node.get_error_backtrace());
                    return;
                }
                evaluation_stack.push(name);
                evaluate_node(node.children.getLast());
                Object value = evaluation_stack.pop();
                if(value.getClass().equals(String.class)){
                    String c = (String)value;
                    evaluation_stack.push(new RWrapper(c));
                    return;
                }else if(value.getClass().equals(RData.class)){
                    RData c = (RData)value;
                    evaluation_stack.push(new RWrapper(c));
                    return;
                }else if(value.getClass().equals(RNode.class)){
                    RNode c = (RNode)value;
                    evaluation_stack.push(new RWrapper(c));
                    return;
                }else{
                    System.out.println("Could not compile field.");
                    System.out.println("Expected: Data|Node|Value. Got: "+value.getClass());
                    System.out.println(node.get_error_backtrace());
                    return;
                }
            default:
                System.out.println("Unrecognized or unimplemented node: "+node.name);
        }
    }
    public RNode build(){
        //Builds a Tree based on root.
        evaluate_node(root);
        RNode result = null;
        try{
            result = (RNode)evaluation_stack.pop();
            if(result==null)throw new Exception();
        }catch (Exception ex){
            System.out.println("Decoding of tree failed.");
            System.out.println(root.get_error_backtrace());
        }
        return result;
    }
}
