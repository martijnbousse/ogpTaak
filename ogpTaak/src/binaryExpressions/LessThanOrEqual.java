package binaryExpressions;

import programrelated.Expression;
import types.BooleanType;
import types.DoubleType;
import types.Type;

public class LessThanOrEqual extends ComparatingExpression {

	public LessThanOrEqual(Expression first, Expression second) {
		super(first, second);
	}

	@Override
	public BooleanType evaluate() {
		Type first = getFirstOperand().evaluate();
		Type second = getSecondOperand().evaluate();
		double firstTerm = ((DoubleType) first).getValue();
		double secondTerm = ((DoubleType) second).getValue();
		
		return new BooleanType(firstTerm <= secondTerm);
	}

}
