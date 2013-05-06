package singleArgumentedExpressions;

import programrelated.Expression;
import types.*;

public class GetXPosition extends EntityRelatedExpression {
	public GetXPosition(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate() {
		Type thisType = getArgument().evaluate();
		double position = ((EntityType) thisType).getValue().getPosition().getXComponent();
		return new DoubleType(position);
	}

}
