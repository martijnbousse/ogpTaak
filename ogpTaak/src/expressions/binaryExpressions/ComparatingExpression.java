package expressions.binaryExpressions;

import expressions.Expression;
import model.ProgramState;
import types.BooleanType;

public abstract class ComparatingExpression extends BinaryExpression {

	protected ComparatingExpression(Expression first, Expression second) {
		super(first, second);
	}
	
	@Override
	public abstract BooleanType evaluate(ProgramState state);
}
