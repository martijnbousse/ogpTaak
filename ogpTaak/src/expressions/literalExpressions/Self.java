package expressions.literalExpressions;

import model.ProgramState;
import types.EntityType;
import types.Type;

public class Self extends LiteralExpression {

	public Self(Type value) {
		super(value);
	}

	@Override
	public Type evaluate(ProgramState state) {
		return new EntityType(state.getSelf());
	}
}
