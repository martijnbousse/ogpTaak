package singleArgumentedExpressions;

import programrelated.Expression;
import types.*;

public class Sinus extends ArithmeticExpression {

	public Sinus(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate() {
		Type thisType = getArgument().evaluate();
		double value = ((DoubleType) thisType).getValue();
		return new DoubleType(Math.sin(value));
	}

}
