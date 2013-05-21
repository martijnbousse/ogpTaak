package expressions.singleArgumentedExpressions;

import expressions.Expression;
import model.ProgramState;
import types.*;

public class GetXPosition extends EntityRelatedExpression {
	public GetXPosition(Expression argument) {
		super(argument);
	}

	@Override
	public DoubleType evaluate(ProgramState state) {
		Type thisType = getArgument().evaluate(state);
		double position = ((EntityType) thisType).getValue().getPosition().getXComponent();
		return new DoubleType(position);
	}

}
