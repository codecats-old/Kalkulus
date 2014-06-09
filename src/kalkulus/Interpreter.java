/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author t
 */
public class Interpreter extends Object{
    
    /*
     * Uwaga:
     *  napisać funkcję formatującą wprowadzone równanie tak aby:
     *  - wszystkie argumenty funkcji były w nawiasach np: 1+integer((6+7*8);3;4)+5
     *      czyli integer(();();()) 
     */
    public void create(String expression){

        //Debugging1
      //  expression="1+integral((6+7*8);3;4)+5";
        expressionList=getToken(expression);
        
    }
    public double calculate(){
        double res = 0;
        if(expressionList!=null){
            ONP onp=new ONP(expressionList);
            res=onp.calculate();
        }
        return res;
    }
    private List<Token> getToken(String e){
        List<Token> tokens=new ArrayList<Token>();
        for(int i=0;i<e.length();i=findEndPos(e,patterns,i)){
            String token=findNext(e, patterns,i);  
            tokens.add(getType(token));
            
        }
        return tokens;
    }
    private Token getType(String t){
        if(match(t,this.patternNumber)){
            return new Number(t);
        }else if(match(t,this.patternConstans)){
            if(t.toLowerCase().equals("pi")) return new PI(null);
            else if(t.toLowerCase().equals("e")) return null;
        }else if(match(t,this.patternOperation)){
            if(t.toLowerCase().equals("+")) return new Addition();
            else if(t.toLowerCase().equals("-")) return new Subtraction();
            else if(t.toLowerCase().equals("*")) return new Multiplication();
            else if(t.toLowerCase().equals("/")) return new Multiplication();
            else if(t.toLowerCase().equals("^")) return new Exponentation();
        }else if(match(t,this.patternFunction)){

            if(t.toLowerCase().equals("sin")) return new Sinus();
            else if(t.toLowerCase().equals("cos")) return null;
            else if(t.toLowerCase().equals("integral")) return new Integral();
        }else if(match(t,this.patternBrackets)){
            if(t.toLowerCase().equals("(")) return new LeftBracket();
            else if(t.toLowerCase().equals(")")) return new RightBracket();
        }else if(t.toLowerCase().equals(";")) return new Comma();
        return null;
    }
    private boolean match(String ex, String pat){
        Pattern p=Pattern.compile(pat);
        Matcher m=p.matcher(ex);      
        return m.find();
    }
    private String findNext(String ex, String pattern, int from){
        return ex.substring(findStartPos(ex,pattern,from), findEndPos(ex,pattern,from));
    }
    private int findStartPos(String ex, String pattern, int startHere){
        ex=ex.substring(startHere);
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(ex);
        m.find();
        return m.start()+startHere;
    }
    private int findEndPos(String ex, String pattern, int startHere){
        ex=ex.substring(startHere);
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(ex);
        m.find();
        return m.end()+startHere;
    }
    
    private List<Token> expressionList=null;
    
    private final String patternComma=";{1}";
    private final String patternNumber="([.]{1}\\d+)|(\\d+[.]{1}\\d*)|(\\d+)";
    private final String patternOperation="[\\^+*/-]{1}";
    private final String patternBrackets="[()]+?";
    private final String patternFunction="(sin)|(integral)";
    private final String patternConstans="(\\We)|(\\Wpi)";
    
    
    private final String patterns="("+
            patternNumber+
            ")|("+
            patternConstans+
            ")|("+
            patternOperation+
            ")|("+
            patternBrackets+
            ")|("+
            patternFunction+
            ")|("+
            patternComma+
            ")";
}
