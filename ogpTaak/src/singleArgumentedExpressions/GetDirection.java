package singleArgumentedExpressions;

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
		double radius = ((EntityType) thisType).getValue().getRadius();
		return new DoubleType(radius);
	}

}
