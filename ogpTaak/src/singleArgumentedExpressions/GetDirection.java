package singleArgumentedExpressions;

import gameObjects.Ship;
import programrelated.Expression;
import types.DoubleType;
import types.EntityType;
import types.Type;

public class GetDirection extends EntityRelatedExpression {

	public GetDirection(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate() {
		Type thisType = getArgument().evaluate();
		double direction = ((Ship)((EntityType) thisType).getValue()).getDirection();
		return new DoubleType(direction);
	}

}
