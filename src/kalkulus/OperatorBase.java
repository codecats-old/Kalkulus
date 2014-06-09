/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulus;

/**
 *
 * @author t
 */
public abstract class OperatorBase extends Token{
    public OperatorBase(int prec){
        super();
        precedence=prec;
        associativity=Associativity.Left;
    }
    public OperatorBase(int prec, Associativity aso){
        super();
        precedence=prec;
        associativity=aso;
    }
    public Double calculate(Double left, Double right){
        return null;
    }
    public void set(Double r){
        result=r;
    }
    public Double get(){
        return result;
    }
    public int getPrecedence(){
        return precedence;
    }
    public Associativity getAssociativity(){
        return associativity;
    }
    private final int precedence;
    private Double result=null;
    private Associativity associativity;
}
