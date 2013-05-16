package binaryExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.BooleanType;
import types.Type;

public class DifferentFrom extends ComparatingExpression {

	public DifferentFrom(Expression first, Expression second) {
		super(first, second);
	}

	@Override
	public BooleanType evaluate(ProgramState state) {
		Type first = getFirstOperand().evaluate(state);
		Type second = getSecondOperand().evaluate(state);
		
		return new BooleanType(first.getValue() != second.getValue());
	}

}
