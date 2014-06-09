/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulus;

/**
 *
 * @author t
 */
public class Subtraction extends OperatorBase{
    public Subtraction(){
        super(1);
    }
    public Double calculate(Double left, Double right){
        Double r=left-right;
        super.set(r);
        return r;
    }
}
