package singleArgumentedExpressions;

import programrelated.Expression;
import types.*;

public class GetRadius extends EntityRelatedExpression {

	public GetRadius(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate() {
		Type thisType = getArgument().evaluate();
		double radius = ((EntityType) thisType).getValue().getRadius();
		return new DoubleType(radius);
	}

}
