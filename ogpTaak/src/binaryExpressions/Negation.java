package binaryExpressions;

import programrelated.Expression;
import types.BooleanType;
import types.Type;

public class Negation extends LogicalExpression {

	public Negation(Expression first) {
		super(first, null);
	}

	@Override
	public BooleanType evaluate() {
		Type thisType = getFirstOperand().evaluate();
		boolean thisValue = ((BooleanType) thisType).getValue();
		
		return new BooleanType( ! thisValue);
	}
}
