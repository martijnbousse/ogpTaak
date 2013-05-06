package singleArgumentedExpressions;

import programrelated.Expression;
import types.DoubleType;

public abstract class ArithmeticExpression extends SingleArgumentExpression {
	
	public ArithmeticExpression(Expression argument) {
		super(argument);
	}
	
	public abstract DoubleType evaluate();
}
