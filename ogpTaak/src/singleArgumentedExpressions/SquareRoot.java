package singleArgumentedExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.DoubleType;
import types.Type;

public class SquareRoot extends ArithmeticExpression {
	public SquareRoot(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate(ProgramState state) {
		Type thisType = getArgument().evaluate(state);
		double value = ((DoubleType) thisType).getValue();
		return new DoubleType(Math.sqrt(value));
	}

}
