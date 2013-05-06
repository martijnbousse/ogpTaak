package binaryExpressions;

import programrelated.Expression;
import types.DoubleType;
import types.Type;

public class Addition extends ArithmeticExpression {

	public Addition(Expression first, Expression second) {
		super(first, second);
	}

	@Override
	public DoubleType evaluate() {
		Type first = getFirstOperand().evaluate();
		Type second = getSecondOperand().evaluate();
		double firstTerm = ((DoubleType) first).getValue();
		double secondTerm = ((DoubleType) second).getValue();
		
		return new DoubleType(firstTerm+secondTerm);
		
	}

}
