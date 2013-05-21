package expressions.singleArgumentedExpressions;

import expressions.Expression;
import model.ProgramState;
import types.*;

public class Sinus extends ArithmeticExpression {

	public Sinus(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate(ProgramState state) {
		Type thisType = getArgument().evaluate(state);
		double value = ((DoubleType) thisType).getValue();
		return new DoubleType(Math.sin(value));
	}

}
