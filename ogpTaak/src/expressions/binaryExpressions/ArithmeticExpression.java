package expressions.binaryExpressions;

import expressions.Expression;
import model.ProgramState;
import types.DoubleType;

public abstract class ArithmeticExpression extends BinaryExpression {

	protected ArithmeticExpression(Expression first, Expression second) {
		super(first, second);
	}
	
	@Override
	public abstract DoubleType evaluate(ProgramState state);
}
