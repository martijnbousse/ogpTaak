package singleArgumentedExpressions;

import programrelated.Expression;
import types.DoubleType;
import types.EntityType;
import types.Type;

public class GetXVelocity extends EntityRelatedExpression {

	public GetXVelocity(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate() {
		Type thisType = getArgument().evaluate();
		double velocity = ((EntityType) thisType).getValue().getVelocity().getXComponent();
		return new DoubleType(velocity);
	}

}
