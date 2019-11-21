package com.rTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.LinkedList;

public class GNode {
    /*
    * Special Node used for parseTree building.
    * */
    public String col,row,name,text;
    public boolean token;
    public LinkedList<GNode> children;
    public static RNode answer = null;

    private static String indenting = "";
    public static String result = "";

    public GNode(String name){
        this.name = name;
        children = new LinkedList<>();
        this.text = null;
        this.row = null;
        this.col = null;
        this.token = false;
    }
    public GNode(LToken t){
        this.token = true;
        this.col = t.col;
        this.row = t.row;
        this.text = t.text;
        this.name = t.name;
        this.children = null;
    }
    public void add(GNode n){
        this.children.add(n);
    }
    //region Visualization Functions:
    public void reset(){
        result = "";
    }
    private void advance(){
        indenting = indenting + "****";
    }
    private  void back(){
        indenting = indenting.substring(0,indenting.length()-4);
    }
    private String begin(GNode n){
        String res = "";
        res =  indenting + "+" + n.name;
        advance();
        return res;
    }
    private String end(GNode node){
        var res = "";
        back();
        res =  indenting + "-" + node.name;
        return res;
    }
    public void printTree(){
        if (this.token)
        {
            result = result + (indenting + this.text)+"\n";
        }
        else
        {
            result = result + (begin(this))+"\n";
            for (GNode child: this.children  ) {
                child.printTree();
            }
            result = result + (end(this))+"\n";
        }
    }

    public String get_error_backtrace(){
        //This will recurse down to first found leaf and return the info of that token.
        GNode aux = this;
        while(!aux.token){
            aux = aux.children.getFirst();
        }
        return "Error in column: "+aux.col+" row: "+aux.row+" under symbol: "+aux.text;
    }
    //endregion

    //region Extra function for simple parsing & visualization.
    public static void build(GNode root){
        answer = null;
        RUniverse main = new RUniverse(root);
        RNode tree = main.build();
        answer = tree;
    }

    public static void test(String file_name){
        printing.initialize("parsed_R_tree_debug.txt");
        File file = new File(file_name);
        if(file.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                String source = "";
                while ((st = br.readLine()) != null) {
                    source = source + st + "\n";
                }
                com.rTree.Lexer lexico = new com.rTree.Lexer(new BufferedReader(new StringReader(source)));
                com.rTree.parser sin = new com.rTree.parser(lexico);
                try {
                    sin.parse();
                } catch (Exception eret) {
                    System.out.println("A syntax error occurred: "+eret.getMessage());
                }
            } catch (Exception e){
                System.out.println("An error occurred while reading file: "+file);
            }
        }

    }
    //endregion
}
