package binaryExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.BooleanType;

public abstract class LogicalExpression extends BinaryExpression {

	protected LogicalExpression(Expression first, Expression second) {
		super(first, second);
	}
	
	public abstract BooleanType evaluate(ProgramState state);
}
