/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulus;

/**
 *
 * @author t
 */
public class Exponentation extends OperatorBase{
    public Exponentation(){
        super(3,Associativity.Right);
    }
    public Double calculate(Double left, Double right){
        
        Double r=Math.pow(left, right);
        super.set(r);
        return r;
    }
}
