package singleArgumentedExpressions;

import model.ProgramState;
import gameObjects.Ship;
import programrelated.Expression;
import types.DoubleType;
import types.EntityType;
import types.Type;

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
