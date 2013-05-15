package singleArgumentedExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.*;

public class GetRadius extends EntityRelatedExpression {

	public GetRadius(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate(ProgramState state) {
		Type thisType = getArgument().evaluate(state);
		double radius = ((EntityType) thisType).getValue().getRadius();
		return new DoubleType(radius);
	}

}
