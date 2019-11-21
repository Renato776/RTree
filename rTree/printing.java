package com.rTree;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class printing {
    public static String selected_file = null;
    public static void initialize(String file_name){
        selected_file = file_name;
        File file2 = new File(file_name);
        if(!file2.exists()){
            try {
                file2.createNewFile();
            } catch(Exception ex){
                System.out.println("An error occurred while creating file: "+file_name);
            }
        }else{
            try {
                PrintWriter writer = new PrintWriter(file2);
                writer.print("");
                writer.close();
            } catch(Exception ex){
                System.out.println("An error occurred while writing text to file: "+file_name);
            }
        }
    }
    public static void lexicalDebugg(String name, String token, Object row, Object column){
        String text = name+" ::=  "+token+" row: "+row+" column: "+column;
        text += "\n";
        try{
            Files.write(Paths.get(selected_file),text.getBytes(), StandardOpenOption.APPEND);
        }catch (Exception ex){
            System.out.println("An error occurred while appending text to file: "+selected_file+"\n Text: "+text);
        }
    }
    public static void print_archive(String name, String content){
        File file = new File(name);
        try{
            file.createNewFile();
            PrintWriter writer = new PrintWriter(name, "UTF-8");
            writer.println(content);
            writer.close();
        }catch (Exception e){
        }
    }
}

