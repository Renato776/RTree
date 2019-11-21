package com.rTree;

import java.io.BufferedReader;
import java.io.StringReader;

public class RTree {
    /*
    * Commercial class. This class is expected to canalize
    * the usage of this whole package.
    * It has no fields and just a few utility functions:
    * */
    public RNode decode(String source){
        /*
        * This method parses the source and then compiles it into a tree data structure which
        * then returns.
        * */
        com.rTree.Lexer lexico = new com.rTree.Lexer(new BufferedReader(new StringReader(source)));
        com.rTree.parser sin = new com.rTree.parser(lexico);
        try {
            sin.parse();
        } catch (Exception eret) {
            System.out.println("A syntax error occurred: "+eret.getMessage());
        }
        if(GNode.answer == null){
            System.out.println("An error occurred while decoding. See error file for details.");
            return null;
        }
        return GNode.answer;
    }
    public RData basic_decode(String source){
        /*
        * This method is intended to be used with trees that have a single node but have all
        * important data held in that node within an RData structure.
        * */
        com.rTree.Lexer lexico = new com.rTree.Lexer(new BufferedReader(new StringReader(source)));
        com.rTree.parser sin = new com.rTree.parser(lexico);
        try {
            sin.parse();
        } catch (Exception eret) {
            System.out.println("A syntax error occurred: "+eret.getMessage());
        }
        /*
        * Alright at this point Build within RData should've already been called. However, how
        * to retrieve the parse tree out of the parsing method? Well, it might not be the best idea,
        * but probably putting the resultant root in a static public variable and then requesting it
        * from here could probably work.
        * */
        if(GNode.answer == null){
            System.out.println("An error occurred while decoding. See error file for details.");
            return null;
        }
        return GNode.answer.data;
    }
    public String basic_encode(String[][] info){
        /*
        * Given a bidimensional String array this method interpretates such array as
        * an array of fields for building an instance of RData. It then provides to build a tree
        * with a single node and this data as content. Then it encodes the result.
        * */
        RData wrapper = new RData();
        for (var field:info) {
            if(!wrapper.contains(field[0])){
                wrapper.add(field[0],field[1]);
            }
            System.out.println("Warning. Skipped duplicated field: "+field[0]);
        }
        return (new RNode(wrapper)).encode();
    }
    public String encode(RNode n){
        //Simply returns n.encode
        return n.encode();
    }
    public String encode (RData data){
        //Returns the encoding of a tree with a single node and data as data.
        RNode wrapper = new RNode(data);
        return wrapper.encode();
    }
}
