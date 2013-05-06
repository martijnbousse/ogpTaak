package singleArgumentedExpressions;

import programrelated.Expression;
import types.DoubleType;
import types.Type;

public class Cosinus extends ArithmeticExpression {

	public Cosinus(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate() {
		Type thisType = getArgument().evaluate();
		double value = ((DoubleType) thisType).getValue();
		return new DoubleType(Math.cos(value));
	}

}
