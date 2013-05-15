package binaryExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.BooleanType;
import types.Type;

public class Negation extends LogicalExpression {

	public Negation(Expression first) {
		super(first, null);
	}

	@Override
	public BooleanType evaluate(ProgramState state) {
		Type thisType = getFirstOperand().evaluate(state);
		boolean thisValue = ((BooleanType) thisType).getValue();
		
		return new BooleanType( ! thisValue);
	}
}
