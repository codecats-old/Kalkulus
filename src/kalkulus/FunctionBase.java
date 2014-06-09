/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulus;

/**
 *
 * @author t
 */
public class FunctionBase extends Token{
    public FunctionBase(int argsC){
        super();
        argsCount=argsC;
    }
    public FunctionBase(){
        super();
        argsCount=1;
    }
    public Double calculate(Double... args){
        return null;
    }
    public Double calculate(Double args){
        return null;
    }
    public int getArgsCount(){
        return argsCount;
    }
    private final int argsCount;
}
