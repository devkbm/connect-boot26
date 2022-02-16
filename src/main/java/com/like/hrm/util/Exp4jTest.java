package com.like.hrm.util;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Exp4jTest {	
	
	public static void main(String[] args) {
		Expression e = new ExpressionBuilder("3 * sin(y) - 2 / (x - 2)")
		        .variables("x", "y")
		        .build()
		        .setVariable("x", 2.3)
		        .setVariable("y", 3.14);
		double result = e.evaluate();
		
		System.out.println(result);
		
	}
	
	
}
