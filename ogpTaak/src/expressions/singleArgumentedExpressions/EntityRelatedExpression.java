package expressions.singleArgumentedExpressions;

import expressions.Expression;
import model.ProgramState;
import types.DoubleType;

public abstract class EntityRelatedExpression extends SingleArgumentExpression {

	protected EntityRelatedExpression(Expression argument) {
		super(argument);
	}
	
	@Override
	public abstract DoubleType evaluate(ProgramState state);
}
