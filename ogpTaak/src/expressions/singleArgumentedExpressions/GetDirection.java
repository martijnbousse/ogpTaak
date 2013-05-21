package expressions.singleArgumentedExpressions;

import expressions.Expression;
import model.ProgramState;
import types.DoubleType;

public class GetDirection extends Expression {

	public GetDirection() {
		super();
	}

	@Override
	public DoubleType evaluate(ProgramState state) {
		double direction = state.getSelf().getDirection();
		return new DoubleType(direction);
	}
}