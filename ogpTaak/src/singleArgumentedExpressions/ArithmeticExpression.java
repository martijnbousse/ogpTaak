package singleArgumentedExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.DoubleType;

public abstract class ArithmeticExpression extends SingleArgumentExpression {
	
	protected ArithmeticExpression(Expression argument) {
		super(argument);
	}
	
	public abstract DoubleType evaluate(ProgramState state);
}
