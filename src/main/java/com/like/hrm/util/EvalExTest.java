package com.like.hrm.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.udojava.evalex.Expression;

public class EvalExTest {

	// https://github.com/uklimaschewski/EvalEx
	public static void main(String[] args) {
		BigDecimal result=null;

	    // Simple usage with an expression without variables.
	    Expression expression=new Expression("1+1/3");
	    result=expression.eval(); // 1.333333
	    // Lowering the precision.
	    expression.setPrecision(2);
	    result=expression.eval(); // 1.3

	    // A more complex expression showing support for unary operators.
	    result=new Expression("(3.4 + -4.1)/2").eval(); // -0.35

	    // Using functions and variables.
	    result=new Expression("SQRT(a^2 + b^2)")
	    .with("a","2.4")
	    .and("b","9.253")
	    .eval(); // 9.5591845

	    // Using pre-created BigDecimals for variables
	    BigDecimal a=new BigDecimal("2.4");
	    BigDecimal b=new BigDecimal("9.235");
	    result=new Expression("SQRT(a^2 + b^2)")
	    .with("a",a)
	    .and("b",b)
	    .eval(); // 9.5591845

	    // Increasing the precision and setting a different rounding mode.
	    result=new Expression("2.4/PI")
	    .setPrecision(128)
	    .setRoundingMode(RoundingMode.UP)
	    .eval(); // 0.763943726841...

	    // Using a function to receive a random number and test it.
	    result=new Expression("random() > 0.5").eval(); // 1

	    // Using more functions and showing the boolean support.
	    result=new Expression("not(x<7 || sqrt(max(x,9,3,min(4,3))) <= 3)")
	    .with("x","22.9")
	    .eval(); // 1

	    // Calling a pre-defined function.
	    result=new Expression("log10(100)").eval(); // 2
	}
}
