/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author t
 */
public class ONP {
    public ONP(List<Token> l){
        expression=l;
        stack=new Stack();
        out=new ArrayList<Token>();
      
       // stack.add(new Double(2));
        //System.out.println(stack.pop());
       // System.out.println(stack.pop());
    }
    public double calculate() {
      
        out=shuntingYard(expression);       
        return calculateRPN(out);
        
    }
    
    private Double calculateRPN(List<Token> rpn){
        stack.clear();
        Iterator it=rpn.iterator();
        
        while(it.hasNext()){
            Token r=(Token)it.next();
            if(r instanceof NumberBase)stack.push(r);
            else if(r instanceof OperatorBase){
                Token a=(Token)stack.pop();
                Token b=(Token)stack.pop();
                Double result=((OperatorBase)r).calculate(((NumberBase)b).get(), ((NumberBase)a).get());
                Number n=new Number(result.toString());
                stack.push(n);
            }else if(r instanceof FunctionBase){         
                FunctionBase func=(FunctionBase)r;
                Double[]  a = new Double[func.getArgsCount()];
                for(int i=0;i<a.length;++i){
                    Token arg=(Token)stack.pop();
                    a[i]=new Double(((NumberBase)arg).get());
                }
                Double result=func.calculate(a);
                Number n=new Number(result.toString());
                stack.push(n);
            }
        }
        Token result=(Token)stack.pop();
        return ((Number)result).get();
    }
    /**
     * infix notation to ONP(RPN)
     */
    private List<Token> shuntingYard(List<Token> expre){
        List<Token> outs=new ArrayList<Token>();
        Iterator it=expre.iterator();
            //DEBUGGING1  
            // int krok=1;
        while(it.hasNext()){
            Token r=(Token)it.next();      
            if(r instanceof NumberBase)outs.add(r);
            else if(r instanceof FunctionBase)stack.push(r);
            else if(r instanceof Comma);
            else if(r instanceof OperatorBase
                    &&!(r instanceof LeftBracket)
                    &&!(r instanceof RightBracket)){
                
                if(!stack.isEmpty()){

                    Token r2=(Token)stack.peek();   
                    while(!stack.isEmpty()&& 
                            ((OperatorBase)r).getPrecedence()<=((OperatorBase)r2).getPrecedence()&&
                            ((OperatorBase)r).getAssociativity()==Associativity.Left
                            ){                       
                        outs.add(((Token)stack.pop()));
                        if(!stack.empty())r2=(Token)stack.peek();
                    }
                }
                stack.push(r);
            }else if(r instanceof LeftBracket){
               
                stack.push(r);
            }else if(r instanceof RightBracket){
                
                Token r2=(Token)stack.pop();
                while(!(r2 instanceof LeftBracket)){
                    outs.add(r2);
                    if(!stack.isEmpty())r2=(Token)stack.pop();
                }
                //function
                if(!stack.isEmpty()){
                    r2=(Token)stack.peek();
                    if(r2 instanceof FunctionBase)
                        outs.add((Token)stack.pop());
                }
            }
            
           
               /*DEBUGGING1 */
                /*
                System.out.println(krok++);
                printStack(); 
                printList(outs);*/

        }
        
        while(!stack.isEmpty()){
           
            outs.add((Token)stack.pop());
        }
        return outs;
    }
    /*
     *     private List<Token> shuntingYard(List<Token> expre){
        List<Token> outs=new ArrayList<Token>();
        Iterator it=expre.iterator();
            //DEBUGGING1  
            // int krok=1;
        while(it.hasNext()){
            Token r=(Token)it.next();
            String symbol=r.getClass().getSuperclass().getSimpleName();            
            if(symbol.equals(NumberBase.class.getSimpleName()))outs.add(r);
            else if(symbol.equals(FunctionBase.class.getSimpleName()))stack.push(r);
            else if(symbol.equals(CommaBase.class.getSimpleName()));
            else if(symbol.equals(OperatorBase.class.getSimpleName())
                    &&!(r.getClass().getSimpleName().equals(LeftBracket.class.getSimpleName()))
                    &&!(r.getClass().getSimpleName().equals(RightBracket.class.getSimpleName()))){
                
                if(!stack.isEmpty()){

                    Token r2=(Token)stack.peek();   
                    while(!stack.isEmpty()&& 
                            ((OperatorBase)r).getPrecedence()<=((OperatorBase)r2).getPrecedence()&&
                            ((OperatorBase)r).getAssociativity()==Associativity.Left
                            ){                       
                        outs.add(((Token)stack.pop()));
                        if(!stack.empty())r2=(Token)stack.peek();
                    }
                }
                stack.push(r);
            }else if(r.getClass().getSimpleName().equals(LeftBracket.class.getSimpleName())){
               
                stack.push(r);
            }else if(r.getClass().getSimpleName().equals(RightBracket.class.getSimpleName())){
                
                Token r2=(Token)stack.pop();
                while(!r2.getClass().getSimpleName().equals(LeftBracket.class.getSimpleName())){
                    outs.add(r2);
                    if(!stack.isEmpty())r2=(Token)stack.pop();
                }
                //function
                if(!stack.isEmpty()){
                    r2=(Token)stack.peek();
                    if(r2.getClass().getSuperclass().getSimpleName().equals(FunctionBase.class.getSimpleName()))
                        outs.add((Token)stack.pop());
                }
            }
            
           


        }
        
        while(!stack.isEmpty()){
           
            outs.add((Token)stack.pop());
        }
        return outs;
    }
     */
    /**
     * 
     */
    private void printStack(){
       Stack copy=new Stack();
       copy.addAll(stack);
       System.out.println("stack:");
       while(!copy.isEmpty()){
           System.out.print(copy.pop().getClass().getSimpleName()+", ");
       }
       System.out.println();
    }
    /**
     * debugging
     * @param l 
     */
    private void printList(List<Token> l){
         Iterator it=l.iterator();
         System.out.println("list:");
         while(it.hasNext()){
            Object ob=it.next();
            if(ob.getClass().getSimpleName().equals(Number.class.getSimpleName()))
                System.out.print(((Number)ob).get()+", ");
            else System.out.print(ob.getClass().getSimpleName()+", ");
         }
         System.out.println("\n____\n");
    }
    private List<Token> expression;
    private Stack stack;
    private List<Token> out;
    
}
