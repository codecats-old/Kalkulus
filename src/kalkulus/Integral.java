/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulus;

/**
 *
 * @author t
 */
public class Integral extends FunctionBase{
    public Integral(){
        super(3);
    }
    public Double calculate(Double ... args){
        System.out.println("Klasa Integral Liczy:");
        for(Double a:args){
            System.out.println(a.toString());
        }
        return new Double(1);
    }
}
