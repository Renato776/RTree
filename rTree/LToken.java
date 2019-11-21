package com.rTree;

public class LToken {
    public String col;
    public String row;
    public String text;
    public String name;
    public LToken(String name, Object col, Object row, String text, boolean format){
        this.name = name;
        this.col = col.toString();
        this.row = row.toString();
        if(format){
            /*
            * An String-like Token. First & Last tokens must be removed and scape secuences must be translated.
            * */
            text = text.trim();
            text = text.substring(1);
            text = text.substring(0,text.length()-1);
            text = translate_scape_sequences(text);
            this.text = text;
        }else{
            this.text = text;
        }
    }
    public static String translate_scape_sequences(String text){
        char[] chars = text.toCharArray();
        int i = 0;
        String res = "";
        while(i<chars.length){
            if(chars[i]=='\\'){
                if((i+1)<chars.length){
                    if(chars[i+1]=='n'){
                        res+= '\n';
                        i+=2;
                        continue;
                    }else if(chars[i+1]=='r'){
                        res += '\r';
                        i+=2;
                        continue;
                    }else if(chars[i+1]=='t'){
                        res += '\t';
                        i+=2;
                        continue;
                    }else if(chars[i+1]=='\"'){
                        res += '\"';
                        i+=2;
                        continue;
                    }else if(chars[i+1]=='\''){
                        res += '\'';
                        i+=2;
                        continue;
                    }else if(chars[i+1]=='\\'){
                        res += '\\';
                        i+=2;
                        continue;
                    }
                }
            }
            res+= chars[i];
            i++;
        }
        return  res;
    }
}
