package expressions.binaryExpressions;

import expressions.Expression;
import model.ProgramState;
import types.BooleanType;

public abstract class LogicalExpression extends BinaryExpression {

	protected LogicalExpression(Expression first, Expression second) {
		super(first, second);
	}
	
	@Override
	public abstract BooleanType evaluate(ProgramState state);
}
