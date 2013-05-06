package binaryExpressions;

import programrelated.Expression;
import types.BooleanType;
import types.Type;

public class Disjunction extends LogicalExpression {

	public Disjunction(Expression first, Expression second) {
		super(first, second);
	}

	@Override
	public BooleanType evaluate() {
		Type first = getFirstOperand().evaluate();
		Type second = getSecondOperand().evaluate();
		boolean firstValue = ((BooleanType) first).getValue();
		boolean secondValue = ((BooleanType) second).getValue();
		
		return new BooleanType(firstValue || secondValue);
		
	}

}
