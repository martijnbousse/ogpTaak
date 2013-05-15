package singleArgumentedExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.DoubleType;
import types.EntityType;
import types.Type;

public class GetYPosition extends EntityRelatedExpression {

	public GetYPosition(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate(ProgramState state) {
		Type thisType = getArgument().evaluate(state);
		double position = ((EntityType) thisType).getValue().getPosition().getYComponent();
		return new DoubleType(position);
	}
}
