package expressions.singleArgumentedExpressions;

import expressions.Expression;
import model.ProgramState;
import types.DoubleType;
import types.Type;

public class Cosinus extends ArithmeticExpression {

	public Cosinus(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate(ProgramState state) {
		Type thisType = getArgument().evaluate(state);
		double value = ((DoubleType) thisType).getValue();
		return new DoubleType(Math.cos(value));
	}

}
