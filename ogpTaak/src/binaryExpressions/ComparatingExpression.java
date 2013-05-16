package binaryExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.BooleanType;

public abstract class ComparatingExpression extends BinaryExpression {

	protected ComparatingExpression(Expression first, Expression second) {
		super(first, second);
	}
	
	public abstract BooleanType evaluate(ProgramState state);
}
