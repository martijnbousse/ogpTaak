package expressions.singleArgumentedExpressions;

import expressions.Expression;
import model.ProgramState;
import types.DoubleType;
import types.EntityType;
import types.Type;

public class GetXVelocity extends EntityRelatedExpression {

	public GetXVelocity(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate(ProgramState state) {
		Type thisType = getArgument().evaluate(state);
		double velocity = ((EntityType) thisType).getValue().getVelocity().getXComponent();
		return new DoubleType(velocity);
	}

}
