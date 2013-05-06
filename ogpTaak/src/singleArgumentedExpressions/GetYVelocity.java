package singleArgumentedExpressions;

import programrelated.Expression;
import types.DoubleType;
import types.EntityType;
import types.Type;

public class GetYVelocity extends EntityRelatedExpression {

	public GetYVelocity(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate() {
		Type thisType = getArgument().evaluate();
		double velocity = ((EntityType) thisType).getValue().getVelocity().getYComponent();
		return new DoubleType(velocity);
	}
}
