/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulus;

/**
 *
 * @author t
 */
public abstract class NumberBase extends Token {
    public NumberBase(String v){
        super();
        value=new Double(Double.valueOf(v));
    }
    public Double get(){
        return value;
    }
    private Double value;

}
