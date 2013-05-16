package binaryExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.DoubleType;

public abstract class ArithmeticExpression extends BinaryExpression {

	protected ArithmeticExpression(Expression first, Expression second) {
		super(first, second);
	}
	
	public abstract DoubleType evaluate(ProgramState state);
}
