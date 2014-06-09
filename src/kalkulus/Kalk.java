/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulus;


/**
 *
 * @author t
 */
public class Kalk {
   
    /*
     * Clears all containers ['numbers' and 'operations']
     */
    public void clear(){

    }
    /**
     * Returns math result for given expression as double number.
     * @param expression
     * @return 
     */
    public double equals(String expression){
        Token t=new Number("4");
        interpreter=new Interpreter();
        interpreter.create(expression);

        return interpreter.calculate();
    }
 
    /** Replace commas to dots.
     * @param expression
     * @return
     */
    public String toDot(String doubleWithComma){
        return doubleWithComma.replace(',', '.');
    }
  
    private Interpreter interpreter;

}
